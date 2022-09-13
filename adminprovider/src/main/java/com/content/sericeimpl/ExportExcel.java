package com.content.sericeimpl;

import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.Boolean;
import java.util.*;
/**
 * 将数据保存到Excel中公共类
 *
 * @author zhush
 * @date 2022/08/16
 */
@Slf4j
@Data
@Service
public class ExportExcel {
    /**
     * 保存文件路径
     * @author zhush
     * @date 2022/08/16
     */
    private String filepath="";
    /**
     *
     * Excel文件的表格头部
     * @author zhush
     * @date 2022/08/16
     */
    private List<String> exceltableheader;
    /**
     *
     * 一页sheet最大的行数
     * @author zhush
     * @date 2022/08/16
     */
    private Integer maxsheetnum;
    /**
     * 合并顺序与优先级列
     * @author zhush
     * @date 2022/08/16
     */
    private List<Integer> ordercoloumlist;
    /**
     * 合并顺序与优先级行
     * @author zhush
     * @date 2022/08/17
     */
    private List<Integer> orderrowList;
    /**
     * excel表格的数据
     * @author zhush
     * @date 2022/08/16
     */
    private List<List<String>> exportdata;
    /**
     * 是否合并单元格，yes/no
     * @author zhush
     * @date 2022/08/16
     */
    private String mode="no";
    /**
     * sheet名称
     * @author zhush
     * @date 2022/08/16
     */
    private String sheetname="sheet1";
    private int sheetnum=0;
    /**
     * 说明
     *
     * @return {@link Boolean }
     * @author zhush
     * @date 2022/08/16
     */
    public Boolean exportexcel() throws IOException, WriteException {
        if("yes".equals(mode)){
            /*
             * 判断文件路径是否存在
             * */
//            filepath="D:\\software\\a.xls";
            if(filepath==null){
                return false;
            }
            File file=new File(filepath);
            WritableWorkbook workbook=Workbook.createWorkbook(file);
            WritableSheet sheet=workbook.createSheet(sheetname,sheetnum);
            /*
             * 写入数据
             * */
            sheet=insertsheet(sheet);
            /*
             * 按照规则进行单元格合并
             * */
            sheet=combineexcel(sheet);
            /*
             * 保存文件
             * */
            workbook.write();
            workbook.close();
            return true;
        }else{
            /*
             * 判断文件路径是否存在
             * */
            if(filepath==null){
                return false;
            }
            File file=new File(filepath);
            WritableWorkbook workbook=Workbook.createWorkbook(file);
            WritableSheet sheet=workbook.createSheet(sheetname,0);
            insertsheet(sheet);
            workbook.write();
            workbook.close();
            return true;
        }
    }
    public File exportexcel2(File file) throws WriteException, IOException {
        WritableWorkbook workbook=Workbook.createWorkbook(file);
        WritableSheet sheet=workbook.createSheet(sheetname,sheetnum);
        /*
         * 写入数据
         * */
        sheet=insertsheet(sheet);
        /*
         * 按照规则进行单元格合并
         * */
        sheet=combineexcel(sheet);
        /*
         * 保存文件
         * */
        log.info("finished:{}",sheet);
        workbook.write();
        workbook.close();
        return file;
    }
    public WritableSheet combineexcel(WritableSheet sheet) throws WriteException {

        Map<Integer,List> map=new HashMap<>();
        Integer allrow=0;
        allrow=sheet.getRows();
        if(ordercoloumlist!=null){
            for (int i=0;i<ordercoloumlist.size();i++){
                List<Integer> location=new ArrayList<>();
                Integer colum=ordercoloumlist.get(i);
                Cell[] cells=sheet.getColumn(colum);
                String content=null;
                for(int j=0;j<allrow;j++){
                    if(content==null){
                        content=cells[j].getContents();
                        location.add(j);
                    }else {
                        if(cells.length>j){
                            if(!content.equals(cells[j].getContents())){
                                location.add(j);
                                content=cells[j].getContents();
                            }else {
                                location.add(0);
                            }
                        }
                    }
                }
                map.put(colum,location);
            }
            Map<Integer,List<Integer[]>> result=new HashMap<>();
            for(int i=0;i<ordercoloumlist.size();i++){
                Integer start=-1;
                Integer end=0;
                List<Integer[]> singlecolum=new ArrayList<>();
                List<Integer> templocation=map.get(ordercoloumlist.get(i));
                //开始的时候，没有任何的数据
                if(result.size()==0){
                    for (int j = 0; j < templocation.size(); j++) {
                        start=templocation.get(j);
                        //判断是否是数组中的最后一个元素
                        if(j+1==templocation.size()){
                            end=allrow;
                        }else {
                            end=templocation.get(j+1)-1;
                        }
                        Integer[] a={start,end};
                        singlecolum.add(a);
                        result.put(ordercoloumlist.get(i),singlecolum);
                    }
                }else{
                    //////
                    Integer pstart=-1;
                    Integer pend=0;
                    start=-1;
                    end=0;
                    Integer now=0;
                    for (int j = 0; j < templocation.size(); j++) {
                        log.info(String.valueOf(j));
                        pstart=result.get(ordercoloumlist.get(i-1)).get(now)[0];
                        pend=result.get(ordercoloumlist.get(i-1)).get(now)[1];
                        //start
                        if(start==-1){
                            start=0;
                        }
                        //end
                        if(j+1==templocation.size()){
                            end=allrow;
                        }else {
                            end=templocation.get(j+1)-1;
                        }
                        //compare
                        if(pend==6){
                            int br=0;
                        }
                        if(pend>end){
                            Integer[] a={start,end};
                            if(start==8&&end==8&&i==2){
                                int fg=0;
                            }
                            singlecolum.add(a);
                            result.put(ordercoloumlist.get(i),singlecolum);
                            start=end+1;
                        }else if(pend<=end){
                            Integer[] a={start,pend};
                            if(start==8&&pend==8&&i==2){
                                int fg=0;
                            }
                            singlecolum.add(a);
                            result.put(ordercoloumlist.get(i),singlecolum);
                            start=pend+1;
                            if(now+1<result.get(ordercoloumlist.get(i-1)).size()){
                                now++;
                            }
                            boolean state=false;
                            if(end==allrow){
                                if(pend!=allrow){
                                    state=true;
                                }
                            }
                            if(pend<end){
                                state=true;
                            }
                            if(state==true){
                                j--;
                            }
                        }

                    }

                }
            }
            log.info(String.valueOf(result.size())+"^^^^^^^^^");
            for (int i = 0; i < ordercoloumlist.size(); i++) {
                for (int j = 0; j < result.get(ordercoloumlist.get(i)).size(); j++) {
                    int a,b,c,d=0;
                    int colum=ordercoloumlist.get(i);
                    a=colum;
                    b=result.get(colum).get(j)[0];
                    c=colum;
                    d=result.get(colum).get(j)[1];
                    if(b!=d&&b!=0&&d!=0){
                        sheet.mergeCells(a,b,c,d);
                    }
                }
            }
        }
        return sheet;
    }
    public WritableSheet insertsheet(WritableSheet sheet) throws WriteException {
        WritableCellFormat wcf = new WritableCellFormat();
        /*
         * 设置第一行表格
         * */
        CellView view = new CellView();
        wcf.setBackground(Colour.YELLOW);
        int row=0;
        if(exceltableheader!=null){
            for(int i=0;i<exceltableheader.size();i++){
                sheet.addCell(new Label(i,row,exceltableheader.get(i),wcf));
                sheet.setColumnView(i, view);
            }
            /*
             * 第一行表格设置完成
             * */
            row++;
        }
        /*
         * 向表格中添加数据
         * */
        //居中
        WritableCellFormat headerFormat = new WritableCellFormat();
        headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        for(int i=0;i<exportdata.size();i++){
            List<String> temp=exportdata.get(i);
            for (int j=0;j<temp.size();j++){
                sheet.addCell(new Label(j,row,temp.get(j),headerFormat));
            }
            row++;
        }
        log.info("数据数目：{}",row);
        return sheet;
    }

}