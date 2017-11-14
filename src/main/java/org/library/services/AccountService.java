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

    public static final String ERR_DELETE_ACCOUNT_ORDER = "errDeleteAccountOrder";
    public static final String ERR_DELETE_ACCOUNT_DELIVERY = "errDeleteAccountDelivery";
    private final static Logger logger = LoggerFactory.getLogger(AccountService.class);

    public static final String passwordREGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?.,/@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String ERR_DELETE_ACCOUNT_ADMIN = "errDeleteAccountAdmin";
    public static final String ERR_DELETE_ACCOUNT_FINES = "errDeleteAccountFines";
    public static final String SUCC_ACCOUNT_DELETED = "succAccountDeleted";
    public static final String ERR_DELETE_ACCOUNT = "errDeleteAccount";
    public static final String SUCC_FINES_SET = "succFinesSet";
    public static final String ERROR_FINES_SET = "errorFinesSet";
    public static final String PROFILE_FAIL = "profileFail";
    public static final String PROFILE_SUCC = "profileSucc";
    public static final String PROFILE_SUCC1 = "profileSucc";
    public static final String ERROR_OLD_PASSWORD = "errorOldPassword";
    public static final String ERROR_CONTAINS_PARTS = "errorContainsParts";
    public static final String SUCC_REGISTER = "succRegister";

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
     * @return result message param
     */
    public String registerUser(@ModelAttribute Reader reader, HttpServletRequest request) {
        String password = reader.getPassword();
        String email = reader.getEmail();

        if (isEmailUnique(email) && isPasswordComplicate(password, email)) {
            reader.setEmail(email.toLowerCase());
            reader.setPassword(DigestUtils.md5Hex(password));
            reader.setFines(0.0);
            reader.setIsAdmin(false);
            reader.setRegistrationDate(new Timestamp(System.currentTimeMillis()));

            readerRepository.save(reader);

            if (loginInSystem(request, password, email)) {
                logger.info("Reader successfully redistricted with id {}", reader.getId());
                return SUCC_REGISTER;
            } else {
                //todo fix message
                return "unsuccessful";
            }
        } else {
            return "unsuccessful";
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
     * @return - result message param
     */
    public String changeReader(@ModelAttribute Reader reader, String oldPassword) {
        Reader curReader = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String newPassword = reader.getPassword();
        try {
            if (newPassword.equals("")) {
                saveReader(reader, curReader);
                return PROFILE_SUCC;
            }
            if (isPasswordComplicate(newPassword, curReader.getEmail())) {
                if (checkOldPassword(oldPassword)) {
                    curReader.setPassword(DigestUtils.md5Hex(newPassword));
                    saveReader(reader, curReader);
                    return PROFILE_SUCC1;
                } else {
                    return ERROR_OLD_PASSWORD;
                }
            } else {
                return ERROR_CONTAINS_PARTS;
            }
        } catch (Exception e) {
            logger.info("Error savind changes for reader {}", reader.getId());
            return PROFILE_FAIL;
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
        logger.info("Change and save info for reader {}", reader.getId());
    }

    /**
     * Change fines for selected user in the database
     *
     * @param readerId - id of user which need to be modified
     * @param fines    - amount of fines to change
     * @return result message param
     */
    public String setFines(int readerId, double fines) {
        try {
            Reader reader = readerRepository.getOne(readerId);
            reader.setFines(fines);
            readerRepository.save(reader);
            logger.info("Fines set successfully {} for reader", fines, readerId);
            return SUCC_FINES_SET;
        } catch (Exception e) {
            logger.info("Set fines error {}", readerId);
        }
        return ERROR_FINES_SET;
    }

    /**
     * Delete reader from database by readerId
     *
     * @param readerId - id of reader to delete
     * @return result message param
     */
    public String deleteReaderById(int readerId) {
        try {
            Reader reader = readerRepository.getOne(readerId);
            if (reader.getIsAdmin()) {
                return ERR_DELETE_ACCOUNT_ADMIN;
            }
            if (reader.getFines() > 0) {
                return ERR_DELETE_ACCOUNT_FINES;
            }
            if (bookOrderRepository.countByReaderId(readerId) > 0) {
                return ERR_DELETE_ACCOUNT_ORDER;
            }
            if (deliveryRepository.countByReaderId(readerId) > 0) {
                return ERR_DELETE_ACCOUNT_DELIVERY;
            }
            readerRepository.delete(readerId);
            logger.info("Delete reader by id {}", readerId);
            return SUCC_ACCOUNT_DELETED;
        } catch (EntityNotFoundException | EmptyResultDataAccessException e) {
            logger.info("Reader delete error {}", readerId);
        }
        return ERR_DELETE_ACCOUNT;
    }
}
