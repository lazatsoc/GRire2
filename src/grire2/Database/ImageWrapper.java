/* This file is part of the GRire project: https://code.google.com/p/grire/
 *
 * GRire is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GRire is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 * Copyright (C) 2014 Lazaros Tsochatzidis <ltsochat at ee.duth.gr>
 */
package grire2.Database;

import java.io.File;
import java.util.HashMap;

public class ImageWrapper {


    private File imgFile;

    private ImageWrapper() {
    }

    public ImageWrapper(File imgFile, String Id) {
        this.imgFile = imgFile;
        this.Id=Id;
        classes=new HashMap<>();
    }


    private HashMap<String, Float> classes;
    private String Id;


    public File getImageFile() {
        return imgFile;
    }

    public HashMap<String,Float> getClasses() {
        return classes;
    }

    public String getId() {
        return Id;
    }

}
