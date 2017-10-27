package org.library.services;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {
    public LibraryController() {}

    @RequestMapping
    (
        value = "/",
        produces = { MediaType.APPLICATION_JSON_VALUE },
        method = RequestMethod.GET
    )
    public ResponseEntity<?> echo () {
        return new ResponseEntity<String>( "Hello World!", HttpStatus.OK );
    }
}
