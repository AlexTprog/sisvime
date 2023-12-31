package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Service.IUploadFilePacService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFilePacService implements IUploadFilePacService {

    private final Logger logp = LoggerFactory.getLogger(getClass());

    private final static String UPLOADS_FOLDER = "uploads";

    @Override
    public Resource load(String filenames) throws MalformedURLException {

        Path pathFoto = getPath(filenames);
        logp.info("pathFoto: " + pathFoto);
        Resource recurso = null;

        recurso = new UrlResource(pathFoto.toUri());
        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
        }

        return recurso;
    }

    @Override
    public String copy(MultipartFile files) throws IOException {

        String uniqueFilename = UUID.randomUUID().toString() + "_" + files.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);


        logp.info("rootPath: " + rootPath);

        Files.copy(files.getInputStream(), rootPath);

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filenames) {
        Path rootPath = getPath(filenames);
        File archivo = rootPath.toFile();

        if (archivo.exists() && archivo.canRead()) {
            if (archivo.delete()) {
                return true;
            }
        }
        return false;
    }

    public Path getPath(String filenames) {
        return Paths.get(UPLOADS_FOLDER).resolve(filenames).toAbsolutePath();
    }

}
