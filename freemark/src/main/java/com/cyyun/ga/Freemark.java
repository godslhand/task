package com.cyyun.ga;


import com.cyyun.ga.vo.DataVO;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by songfl on 2016/12/19.
 */
public class Freemark {

    public void export() throws Exception {
            Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        System.out.println(getClass().getClassLoader().getResource(""));
        String path = getClass().getClassLoader().getResource("template").getPath();

        configuration.setDirectoryForTemplateLoading(new File(path));//此处是本类Class.getResource()相对于模版文件的相对路径
        Template template = null;
        File file  = new File(path+"/workbook.xml");
        System.out.println(file.exists());
        File outFile = new File(getClass().getClassLoader().getResource("").getPath()+"/result.xls");
        Writer writer = null;
        template = configuration.getTemplate("workbook.ftl");
        template.setEncoding("utf-8");
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), Charset.forName("utf-8")));//此处为输 出文档编码
        HashMap<String,List<DataVO>> map = new HashMap<String, List<DataVO>>();
        map.put("list",initDate());
         template.process(map, writer);
        writer.flush();writer.close();
    }

    private List<DataVO> initDate(){
        List<DataVO> list = new ArrayList<DataVO>();
        DataVO vo = new DataVO();
        vo.setFinshTime(new Date());
        vo.setMissionId(58298);
        vo.setMissionName("===竣工验收单收回"+new Date());
        vo.setProjectId(50);
        vo.setProjectName("小学施工");
        vo.setReporter("张三");
        vo.setMissionStatus("审核中");
        list.add(vo);
        return  list;
    }

    public static void main(String[] args) throws Exception {
        Freemark freemark =  new Freemark();
        freemark.export();

    }
}
