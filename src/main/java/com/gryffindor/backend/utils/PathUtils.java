package com.gryffindor.backend.utils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class PathUtils {
    public static String getPathFromUri(String uriStr) {
        try {
            URI uri = new URI(uriStr);
            return new File(uri.getSchemeSpecificPart()).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
