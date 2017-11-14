package org.library.services;

import org.library.db.domain.Reader;
import org.library.db.repo.BookOrderRepository;
import org.library.db.repo.DeliveryRepository;
import org.library.db.repo.ReaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AccountService {

    private final static Logger logger = LoggerFactory.getLogger(AccountService.class);


    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BookOrderRepository bookOrderRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

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
            logger.info("delete reader by id" + readerId);
            return 0;
        } catch (EntityNotFoundException e) {
            logger.error(e.getStackTrace().toString());
        }
        return 5;
    }
}
