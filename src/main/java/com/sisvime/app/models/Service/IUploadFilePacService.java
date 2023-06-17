package com.sisvime.app.models.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IUploadFilePacService {


    public Resource load(String filenames) throws MalformedURLException;

    public String copy(MultipartFile files) throws IOException;

    public boolean delete(String filenames);
}
