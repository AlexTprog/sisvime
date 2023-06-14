package com.sisvime.app.share;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    protected ModelMapper modelMapper;
}
