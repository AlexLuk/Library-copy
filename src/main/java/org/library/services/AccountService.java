package org.library.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.library.db.domain.Reader;
import org.library.db.repo.BookOrderRepository;
import org.library.db.repo.DeliveryRepository;
import org.library.db.repo.ReaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Service
public class AccountService {

    private final static Logger logger = LoggerFactory.getLogger(AccountService.class);

    public static final String passwordREGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?.,/@#$%^&+=])(?=\\S+$).{8,}$";

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BookOrderRepository bookOrderRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    /**
     * Save new reader in the db
     *
     * @param reader  - new reader data
     * @param request
     * @return true if operation was successful
     */
    public boolean registerUser(@ModelAttribute Reader reader, HttpServletRequest request) {
        String password = reader.getPassword();
        String email = reader.getEmail();

        if (isEmailUnique(email) && isPasswordComplicate(password, email)) {
            reader.setEmail(email.toLowerCase());
            reader.setPassword(DigestUtils.md5Hex(password));
            reader.setFines(0.0);
            reader.setIsAdmin(false);
            reader.setRegistrationDate(new Timestamp(System.currentTimeMillis()));

            readerRepository.save(reader);

            return loginInSystem(request, password, email);
        } else {
            return false;
        }
    }

    public boolean isEmailUnique(String email) {
        return !readerRepository.findByEmail(email.toLowerCase()).isPresent();
    }

    /**
     * Create session for new user
     *
     * @param request
     * @param password - password of new user
     * @param email    - email of new user
     * @return true if operation was successful
     */
    private boolean loginInSystem(HttpServletRequest request, String password, String email) {
        try {
            request.getSession();
            request.login(email, password);
        } catch (Exception se) {
            logger.warn("Authorization error " + se);
            return false;
        }
        return true;
    }

    /**
     * Check user input for validity based on REGEXP
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     * login should not be in password in any case
     *
     * @param password password from user input
     * @param email    email from user input
     * @return true if password valid for our
     */
    public Boolean isPasswordComplicate(String password, String email) {
        if (email.length() == 0) {
            return false;
        }
        String userLogin;
        if (email.contains("@")) {
            userLogin = email.substring(0, email.indexOf("@"));
        } else {
            return false;
        }
        if (userLogin.length() == 0) {
            return false;
        }
        return password.matches(passwordREGEXP) && !password.toLowerCase().matches(".*?" + userLogin + ".*?");
    }

    /**
     * Change reader info in database for the the info from user profile form
     *
     * @param reader - reader object filled with user input
     * @return - 0 - success,
     * 1 - failure because old password is wrong,
     * 2 - failure because email is in password
     * 3 - exception in process
     */
    public int changeReader(@ModelAttribute Reader reader, String oldPassword) {
        Reader curReader = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String newPassword = reader.getPassword();
        try {
            if (newPassword.equals("")) {
                saveReader(reader, curReader);
                return 0;
            }
            if (isPasswordComplicate(newPassword, curReader.getEmail())) {
                if (checkOldPassword(oldPassword)) {
                    curReader.setPassword(DigestUtils.md5Hex(newPassword));
                    saveReader(reader, curReader);
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 2;
            }
        } catch (Exception e) {
            return 3;
        }
    }

    /**
     * Check if user insert valid current password
     *
     * @param oldPassword - password which need to be checked as existing in the database for current user
     * @return true if password is right
     */
    public boolean checkOldPassword(String oldPassword) {
        Reader curReader = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return curReader.getPassword().equals(DigestUtils.md5Hex(oldPassword));
    }

    /**
     * Save changed reader in the database
     *
     * @param reader    - reader which need to be modified
     * @param curReader - reader which need to be modified
     */
    private void saveReader(@ModelAttribute Reader reader, Reader curReader) {
        curReader.setLastName(reader.getLastName());
        curReader.setFirstName(reader.getFirstName());
        curReader.setPatronymic(reader.getPatronymic());
        readerRepository.save(new Reader(curReader));
    }

    /**
     * Change fines for selected user in the database
     *
     * @param readerId - id of user which need to be modified
     * @param fines    - amount of fines to change
     * @return true if operation was successful
     */
    public boolean setFines(int readerId, double fines) {
        try {
            Reader reader = readerRepository.getOne(readerId);
            reader.setFines(fines);
            readerRepository.save(reader);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //todo return string property instead of int
    /**
     * Delete reader from database by readerId
     *
     * @param readerId - id of reader to delete
     * @return 0 - successful delete
     * 1- delete rejected, user is admin
     * 2- delete rejected, user has fines
     * 3- delete rejected, user has orders
     * 4- delete rejected, user has deliveries on hand
     * 5- delete rejected, delete error
     */
    public int deleteReaderById(int readerId) {
        try {
            Reader reader = readerRepository.getOne(readerId);
            if (reader.getIsAdmin()) {
                return 1;
            }
            if (reader.getFines() > 0) {
                return 2;
            }
            if (bookOrderRepository.countByReaderId(readerId) > 0) {
                return 3;
            }
            if (deliveryRepository.countByReaderId(readerId) > 0) {
                return 4;
            }
            readerRepository.delete(readerId);
            logger.info("delete reader by id {}",readerId);
            return 0;
        } catch (EntityNotFoundException | EmptyResultDataAccessException e) {
            logger.error("reader delete error", e);
        }
        return 5;
    }
}
