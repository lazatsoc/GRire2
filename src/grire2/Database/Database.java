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

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Database implements Iterable<ImageWrapper>{
    private HashMap<String, ImageWrapper> Images;

    private Database(){
        Images=new HashMap<>();
    }

    private Database(List<ImageWrapper> _images) {

        for (ImageWrapper img : _images)
            Images.put(img.getId(), img);
    }

    public void add(ImageWrapper img, boolean replace) throws ImageAlreadyExistsException{
        if (Images.containsKey(img.getId()) && !replace) throw new ImageAlreadyExistsException();
        Images.put(img.getId(), img);
    }

    public void add(ImageWrapper img){
        Images.put(img.getId(), img);
    }

    public ImageWrapper get(String id){
        return Images.get(id);
    }

    public static Database createDatabase(String folder, String qrels, List<String> extensionsFilter) throws IOException{
        File[] files=new File(folder).listFiles(f -> {
            String substring = f.getName().substring(f.getName().lastIndexOf('.')+1);
            return extensionsFilter.contains(substring);
        });
        Database db=new Database();
        for (File f : files) {
            String id=f.getName().substring(0,f.getName().lastIndexOf('.'));
                db.add(new ImageWrapper(f, id));
        }
        BufferedReader br = new BufferedReader(new FileReader(qrels));
        String line;
        String[] parts;
        while ((line = br.readLine()) != null) {
            parts=line.split(" ");
            if (parts[3].equals("1")) db.get(parts[2]).getClasses().add(parts[0]);
        }
        br.close();
        return db;
    }

    public static Database loadDatabase(String name) throws IOException{
        XStream xStream=new XStream();
        return (Database) xStream.fromXML(new FileReader(name));
    }

    public static void saveDatabase(Database db, String file) throws IOException{
        XStream xStream=new XStream();
        xStream.toXML(db, new FileWriter(file, false));
    }

    @Override
    public Iterator<ImageWrapper> iterator() {
        return Images.values().iterator();
    }

    public Stream<ImageWrapper> asStream() {
        return Images.values().stream();
    }

    public class ImageAlreadyExistsException extends Exception{

    }
}


