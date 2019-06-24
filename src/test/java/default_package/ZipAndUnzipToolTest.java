package default_package;

import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class ZipAndUnzipToolTest {
    Comparator<String> comparator=new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    };


    @org.junit.Test
    public void unTarFileWithoutDir() throws IOException {
        List<String> expectedList=new ArrayList<>();
        expectedList.add("file1.txt");
        expectedList.add("file2.txt");
        expectedList.sort(comparator);
        File file=new File("src\\test\\resources\\dir_without_dir.tar");

        List<String> list=ZipAndUnzipTool.unTarFile2(file);
        list.sort(comparator);

        Assert.assertEquals(expectedList,list);

    }

    @org.junit.Test
    public void unTarFileWithDir() throws IOException {
        List<String> expectedList=new ArrayList<>();
        expectedList.add("file1.txt");
        expectedList.add("file2.txt");
        expectedList.add("dirInside/");
        expectedList.sort(comparator);
        File file=new File("src\\test\\resources\\dir.tar");

        List<String> list=ZipAndUnzipTool.unTarFile2(file);
        list.sort(comparator);

        Assert.assertEquals(expectedList,list);

    }
}
