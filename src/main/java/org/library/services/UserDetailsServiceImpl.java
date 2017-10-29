package org.library.services;

import org.library.db.models.UserDetailsImpl;
import org.library.db.models.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.library.repository.ReadersRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ReadersRepository readersRepository;

    @Override
    public UserDetails loadUserByUsername(String readerEmail) throws UsernameNotFoundException {
        Optional<Reader> optionalReader = readersRepository.findReaderByEmail(readerEmail);
        optionalReader.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalReader
                .map(UserDetailsImpl::new).get();
    }
}
