package org.library.services;

import org.library.db.domain.Reader;
import org.library.db.domain.LibraryUserDetails;
import org.library.db.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public LibraryUserDetails loadUserByUsername(String readerEmail) throws UsernameNotFoundException {
        Optional<Reader> optionalReader = readerRepository.findByEmail(readerEmail);
        optionalReader.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalReader
                .map(LibraryUserDetails::new).get();
    }
}
