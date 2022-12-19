package com.common.moduleclass;
import jxl.Cell;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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
    private List<String> head;
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
    private List<List<String>> data;
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
    //start,开始的坐标[pass]
    public List<Integer> getDifferentCol(List<String> col,Integer start){
        List<Integer> newcol=new ArrayList<>();
        int allrow= col.size();
        String lastcontent="";
        for (int i=0;i< col.size();i++){
            if (i<start){
                newcol.add(i);
                continue;
            }else {
                if ("".equals(lastcontent)){
                    lastcontent=col.get(i);
                    newcol.add(i);
                }else if(!lastcontent.equals(col.get(i))){
                    newcol.add(i);
                    lastcontent=col.get(i);
                }
            }
        }
        return newcol;
    }
    //生成不同的区域数组
    public List<ArrayList> placeArray(Integer allrow,List<Integer> col){
        List<ArrayList> newcol=new ArrayList<>();
        for (int i=0;i<col.size();i++){
            ArrayList<Integer> one=new ArrayList<>();
            if(i+1== col.size()){
                one.add(col.get(i));
                one.add(allrow-1);
            }else {
                one.add(col.get(i));
                one.add(col.get(i+1)-1);
            }
            newcol.add(one);
        }
        return newcol;
    }

    public WritableSheet insertdata(WritableSheet sheet) throws WriteException {
        int row=0;
        WritableCellFormat writableCellFormat=new WritableCellFormat();
        writableCellFormat.setBackground(Colour.YELLOW);
        WritableCellFormat headerFormat = new WritableCellFormat();
        headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        if(head.size()>0){
            for (int i=0;i< head.size();i++) {
                sheet.addCell(new Label(i,row,head.get(i),writableCellFormat));
                sheet.setColumnView(i,20);
            }
            row++;
        }
        for(int i=0;i< data.size();i++){
            List<String> linedata=data.get(i);
            for (int j=0;j<linedata.size();j++){
                if(row>0){
                    sheet.addCell(new Label(j,i+1,linedata.get(j),headerFormat));
                }else {
                    sheet.addCell(new Label(j,i,linedata.get(j),headerFormat));
                }
            }
            row++;
        }
        log.info("插入Excel中的表格数目为：{}",row);
        return sheet;
    }

    public File exportFile() throws IOException, WriteException {
        if("".equals(filepath)){
            return null;
        }
        int start=0;
        if(head.size()>0){
            start=1;
        }
        File file=new File(filepath);
        WritableWorkbook writableWorkbook= Workbook.createWorkbook(file);
        WritableSheet sheet=writableWorkbook.createSheet("sheet1",1);
        sheet=insertdata(sheet);
        if(mode.equals("yes")){
            List<List> allcolplace=new ArrayList<>();
            int allrow=sheet.getRows();
            for (int i=0;i<ordercoloumlist.size();i++){
                Cell[] cells=sheet.getColumn(ordercoloumlist.get(i));
                List<String> col=getlist(cells);
                start=0;
                List<Integer> colint=getDifferentCol(col,start);
                List<ArrayList> colarray=placeArray(allrow,colint);
                if(allcolplace.size()>0){
                    colarray=twoArray(allcolplace.get(i-1),colarray,allrow);
                }
                allcolplace.add(colarray);
            }

            List<List> combine=new ArrayList<>();
            for (int i=0;i<allcolplace.size();i++){
                if(i == 0){
                    combine.add(changearray(allcolplace.get(i),allrow));
                }else {
//                    allcolplace.get(i)=twoArray(allcolplace.get(i-1),allcolplace.get(i),allrow)
                    combine.add(changearray(allcolplace.get(i-1),allcolplace.get(i),allrow));
                }
            }
            for (int i=0;i<ordercoloumlist.size();i++){
                int col=ordercoloumlist.get(i);
                List colnow= combine.get(i);
                int start1=0;
                for (int j=0;j<colnow.size();j++){
                    int lenth= (int) colnow.get(j);
                    if(lenth==1){
                        sheet.mergeCells(col,start1,col,start1);
                        start1++;
                    }else {
                        sheet.mergeCells(col,start1,col,start1+lenth-1);
                        start1=start1+lenth;
                    }
                }
            }
        }
        writableWorkbook.write();
        writableWorkbook.close();
        return file;
    }
    public List<String> getlist(Cell[] cells){
        List<String> result=new ArrayList<>();
        for (Cell cell:cells) {
            result.add(cell.getContents());
        }
        return result;
    }
    public List<Integer> changearray(List<ArrayList> col,Integer allrow){
        List<Integer> result=new ArrayList<>();
        for (int i=0;i<col.size();i++){
            int a= (int) col.get(i).get(0);
            int b= (int) col.get(i).get(1);
            result.add(b-a+1);
        }
        return result;
    }
    public List<Integer> changearray(List<ArrayList> lastcol,List<ArrayList> col,Integer allrow){
        List<Integer> change=new ArrayList<Integer>();
        int tempstart=0;
        int tempend=0;
        int indexlast=0;
        int indexnow=0;
        for (int i=0;i<allrow;i++){
            Integer lasta= (Integer) lastcol.get(indexlast).get(0);
            Integer lastb=(Integer) lastcol.get(indexlast).get(1);
            Integer nowa= (Integer) col.get(indexnow).get(0);
            Integer nowb= (Integer) col.get(indexnow).get(1);
            tempend++;
            if(tempend>lastb||tempend>nowb){
                change.add(tempend-tempstart);
                tempstart=tempend;
                if(tempend>lastb){
                    indexlast++;
                }
                if (tempend>nowb){
                    indexnow++;
                }
            }
        }
        return change;
    }
    public List<ArrayList> twoArray(List<ArrayList> lastcol,List<ArrayList> col,Integer allrow){
        List<ArrayList> newcol=new ArrayList<>();
        int tempstart=0;
        int tempend=0;
        int indexlast=0;
        int indexnow=0;
        for (int i=0;i<allrow;i++){
            Integer lasta= (Integer) lastcol.get(indexlast).get(0);
            Integer lastb=(Integer) lastcol.get(indexlast).get(1);
            Integer nowa= (Integer) col.get(indexnow).get(0);
            Integer nowb= (Integer) col.get(indexnow).get(1);
            tempend++;
            if(tempend>lastb||tempend>nowb){
                ArrayList one=new ArrayList();
                one.add(tempstart);
                one.add(tempend-1);
                newcol.add(one);
                tempstart=tempend;
                if(tempend>lastb){
                    indexlast++;
                }
                if (tempend>nowb){
                    indexnow++;
                }
            }
        }
        return newcol;
    }
}
