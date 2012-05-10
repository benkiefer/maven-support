package org.burgers.maven.plugins;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MkDirMojoTest {
    MkDirMojo mojo;
    File file;
    File tempDirectory;
    File file1;
    File file2;


    @Before
    public void setUp() throws IOException {
        file = File.createTempFile("temp", ".txt");
        tempDirectory = file.getParentFile();
        file1 = new File(tempDirectory, "mkDirOne");
        file2 = new File(tempDirectory, "mkDirTwo");
        mojo = new MkDirMojo();
    }

    @Test
    public void execute() throws MojoExecutionException {
        File[] files = new File[]{file1, file2};

        mojo.setDirectories(files);
        mojo.execute();

        for (File myFile : files) {
            assertTrue(myFile.exists());
            assertTrue(myFile.isDirectory());
        }
    }

    @Test
    public void execute_no_files() {
        try {
            mojo.execute();
            fail();
        } catch (MojoExecutionException e) {
            assertTrue(e.getMessage() == "Must have at least one directory to create");
        }
    }

    @Test
    public void execute_empty_files() {
        try {
            mojo.setDirectories(new File[]{});
            mojo.execute();
            fail();
        } catch (MojoExecutionException e) {
            assertTrue(e.getMessage() == "Must have at least one directory to create");
        }
    }

    @Test
    public void execute_directory_exists_and_is_a_file() {
        try {
            mojo.setDirectories(new File[]{file});
            mojo.execute();
            fail();
        } catch (MojoExecutionException e) {
            assertTrue(e.getMessage().equals(file.getName() + " is a file."));
        }
    }

    @Test
    public void execute_directory_is_a_directory_that_already_exist() throws MojoExecutionException{
        mojo.setDirectories(new File[]{tempDirectory});
        mojo.execute();
        assertTrue(tempDirectory.exists());
        assertTrue(tempDirectory.isDirectory());
    }

    @After
    public void tearDown() {
        file.delete();
        file1.delete();
        file2.delete();
    }
}
