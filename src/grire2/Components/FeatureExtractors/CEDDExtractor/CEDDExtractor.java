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
 * Copyright (C) 2013 Lazaros Tsochatzidis <ltsochat at ee.duth.gr>
 */
package grire2.Components.FeatureExtractors.CEDDExtractor;

import grire2.Components.Interfaces.FeatureExtractor;
import grire2.Database.ImageWrapper;
import javafx.concurrent.Task;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Arrays;

public class CEDDExtractor extends FeatureExtractor {

    CEDD cedd = new CEDD();

    @Override
    public float[][] extract(ImageWrapper img) {
        try {
            cedd.extract(ImageIO.read(img.getImageFile()));
            return new float[][] {Arrays.copyOf(cedd.data, cedd.data.length)};
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Task setUp(Object... args) throws Exception {
        return null;
    }

    @Override
    public Class getComponentInterface() {
        return null;
    }

    @Override
    public Class[] getParameterTypes() {
        return new Class[0];
    }

    @Override
    public Class[] getSetUpParameterTypes() {
        return new Class[0];
    }

    @Override
    public String[] getParameterNames() {
        return new String[0];
    }

    @Override
    public String[] getSetUpParameterNames() {
        return new String[0];
    }

    @Override
    public String[] getDefaultParameterValues() {
        return new String[0];
    }

    @Override
    public String[] getDefaultSetUpParameterValues() {
        return new String[0];
    }

    @Override
    public boolean requiresSetUp() {
        return false;
    }
}
