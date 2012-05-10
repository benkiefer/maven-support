package org.burgers.maven.support.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;

public class MkDirMojo extends AbstractMojo {
    /**
     * @goal mkDir
     */

    /**
     * @parameter
     */
    private File[] directories;

    public void execute() throws MojoExecutionException {
        if (directories != null && directories.length != 0) {
            for (File file : directories) {
                if (file.isFile()){
                    throw new MojoExecutionException(file.getName() + " is a file.");
                }
                else if (!file.exists()) {
                    file.mkdir();
                }
            }
        } else {
            throw new MojoExecutionException("Must have at least one directory to create");
        }
    }

    public void setDirectories(File[] directories) {
        this.directories = directories;
    }


}
