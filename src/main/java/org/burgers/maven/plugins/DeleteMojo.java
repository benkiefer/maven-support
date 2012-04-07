package org.burgers.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;

import java.io.File;

public class DeleteMojo extends AbstractMojo {
    /*
   * @goal delete
    */

    /*
    * @parameter
     */
    private File[] files;

    public void execute() {
        for (File file : files) {
            if (file.isDirectory()) deleteDirectory(file);
            else file.delete();
        }
    }

    private void deleteDirectory(File file) {
        if (file.exists()) {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else {
                    f.delete();
                }
            }
            file.delete();
        }
    }

    public void setFiles(File[] files) {
        this.files = files;
    }
}
