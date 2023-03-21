package com.common.moduleclass;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {说明}导出excel version2
 *
 * @author littledotboy
 * @date 2023/03/21
 */
@Data
@Service
@Slf4j
public class ExportExcelVersion2 {

    /**
     * Excel文件名称
     */
    private String fileName;

    /**
     * 文件保存路径
     */
    private String filePath;

    /**
     * 设置一页sheet最大的行数
     */
    private Integer sheetMaxRows=5000;

    /**
     * 合并选项的mode，默认是false，不合并
     */
    private Boolean mode=false;

    /**
     * 要合并的列角标
     */
    private List<Integer> combineColums;
    /**
     * 新的sheet名称
     */
    private String sheetName="sheet1";

    /**
     * Excel文件新增或是删除
     */
    private Boolean excelAdd=true;

    /**
     * Excel文件
     */
    private File excelFile;

    private List<String> sheetHeaders;
    private Colour headerColor;
    /**
     * Excel中相关的数据
     */
    private List<List<String>> insertData;
    /**
     * 建立Excel文件
     */
    public void createExcelFile(){
        String[] last=fileName.split(".");
        if (last.length<1){
            fileName=fileName+".xls";
        }
        try {
            String fileUrl=filePath+"/"+fileName;
            excelFile=new File(fileUrl);
        }catch (Exception e){
            log.info("创建Excel文件发生错误，路径为{}",filePath+"/"+fileName);
        }
    }

    /**
     * 向文件中插入数据
     */
    public void insertSheet() throws IOException, WriteException {
        WritableWorkbook writableWorkbook= Workbook.createWorkbook(excelFile);
        Integer rows=insertData.size();
        int count=(int)Math.floor(rows/sheetMaxRows)+1;
        int sheetnum=writableWorkbook.getNumberOfSheets();
        for (int i = 0; i < count; i++) {
            String sheetString=sheetName+i;
            if (sheetnum!=0){
                WritableSheet sheet=writableWorkbook.createSheet(sheetString,sheetnum+i);
                int start=i*sheetMaxRows;
                int end=(i+1)*sheetMaxRows-1;

//                singleSheet(sheet,sheetString,start,end);
//                startCombine(sheet);
//                getCombineIndex(sheet);
            }else {
                WritableSheet sheet1=writableWorkbook.createSheet(sheetString,i);
                int start=i*sheetMaxRows;
                int end=(i+1)*sheetMaxRows-1;
//                singleSheet(sheet1,sheetString,start,end);
//                startCombine(sheet1);
//                getCombineIndex(sheet);
            }
        }
        writableWorkbook.write();
        writableWorkbook.close();
    }

    /**
     * @param sheet
     * @param tempSheetName
     * @param dataColumIndex
     * @param dataColumEnd
     * @return {@link WritableSheet}
     * @throws WriteException
     */
    public void singleSheet(WritableSheet sheet,String tempSheetName,int dataColumIndex,int dataColumEnd) throws WriteException {
        sheet.setName(tempSheetName);
        CellView view=new CellView();
        view.setAutosize(true);
        WritableCellFormat writableCellFormat=new WritableCellFormat();
        writableCellFormat.setBackground(headerColor);
        Boolean header=false;
        if (sheetHeaders.size()>0){
            header=true;
        }
        for (int i=0;i<sheetHeaders.size();i++){
            Label label=new Label(0,i,sheetHeaders.get(i),writableCellFormat);
            sheet.addCell(label);
            sheet.setColumnView(i,view);
        }
        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        for (int i = 0; i < insertData.size(); i++) {
            if(dataColumIndex!=0){
                i=dataColumIndex;
            }
            if (i>dataColumEnd){
                break;
            }
            List<String> tempColum=insertData.get(i);
            for (int j = 0; j < tempColum.size(); j++) {
                if (header){
                    Label singleCell=new Label(i+1,j,tempColum.get(j),cellFormat);
                    sheet.addCell(singleCell);
                }else {
                    Label singleCell2=new Label(i,j,tempColum.get(j),cellFormat);
                    sheet.addCell(singleCell2);
                }
            }
        }
    }

    /**
     * @param sheet
     * @return {@link List}<{@link ArrayList}<{@link Integer}>>
     */
    public void getCombineIndex(WritableSheet sheet) throws WriteException {
        int start=0;
        if(sheetHeaders.size()>0){
            start=1;
        }
        if(mode){
            List<List> allcolplace=new ArrayList<>();
            int allrow=sheet.getRows();
            for (int i=0;i<combineColums.size();i++){
                Cell[] cells=sheet.getColumn(combineColums.get(i));
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
            for (int i=0;i<combineColums.size();i++){
                int col=combineColums.get(i);
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

    public File returnExcelFile(){
        return excelFile;
    }

    public static void main(String[] args) {
        double target = 22.2;

        // 向上取整。
        System.out.println(Math.ceil(target));

        // 向下取整。
        System.out.println(Math.floor(target));
    }



    //
//
//        List<ArrayList<Integer>> arrayLists=new ArrayList<>();
//        if (combineColums.size()>0){
//            int columNum=sheet.getRows();
//            List<Integer> oldArray=new ArrayList<>();
//            List<Integer> newArray=new ArrayList<>();
//            for (int i=0;i<combineColums.size();i++){
//                String tempString=null;
//                int index=combineColums.get(i);
//                for (int j=0;j<columNum;j++){
//                    int columIndex=combineColums.get(i);
//                    if (oldArray==null){
//                        if (mode){
//                            Cell[] row=sheet.getRow(j);
//                            if (StringUtil.isEmpty(tempString)){
//                                tempString=row[index].getContents();
//                            }
//                            if (StringUtil.isUnEmpty(row[index].getContents())){
//
//                            }
//                        }
//
//                    }else {
//
//
//
//                    }
//
//                }
//
//
//
//            }
//
//
//            return arrayLists;
//        }else {
//            return null;
//        }
//    }
    /**
     * @param sheet 开始对传入的sheet进行相关的合并单元格,这种方法重写之前的ExportExcel中的相关的代码
     *              暂时还未进行相关得测试与验证
     * @return {@link WritableSheet}
     */
    public void startCombine(WritableSheet sheet) throws WriteException {
        /*
         * First step is get row count of this sheet
         * and compare these data
         * if we find same data we'll combine these cells in this sheet
         * */
        int columnum=sheet.getColumns();
        List<ArrayList<Integer>> arrayLists=new ArrayList<>();
        if (sheetHeaders.size()>0) {
            for (int j = 0; j < combineColums.size(); j++) {
                ArrayList<Integer> onecol = new ArrayList<>();
                int colindex = combineColums.get(j);
                Cell[] cells = sheet.getRow(colindex);
                String tempstring = "";
                for (int k = 0; k < cells.length; k++) {
                    if (sheetHeaders.size() > 0 && k == 0) {
                        continue;
                    }
                    if (StringUtil.isEmpty(tempstring)) {
                        tempstring = cells[k].getContents();
                        if (k+1!= cells.length){
                            onecol.add(k);
                        }
                    } else {
                        String nowstring = cells[k].getContents();
                        if (!tempstring.equals(nowstring)) {
                            if (k+1!= cells.length){
                                onecol.add(k);
                            }
                        }
                    }
                    if (k + 1 == cells.length) {
                        onecol.add(k + 1);
                    }
                }
                arrayLists.add(onecol);
            }
        }
        for (int i=1;i<arrayLists.size();i++){
            ArrayList<Integer> array1=arrayLists.get(i-1);
            for (int j=0;j<array1.size();j++){
                if (!arrayLists.get(i).contains(array1.get(j))){
                    arrayLists.get(i).add(array1.get(j));
                }
            }
            Collections.sort(arrayLists.get(i));
        }
        /*
         * Second step is sort these arraylist we got
         * and we'll combine these cells depend on it.
         * */
        for (int i=0;i<arrayLists.size();i++){
            ArrayList<Integer> arrayList=arrayLists.get(i);
            int tempint=-1;
            if (sheetHeaders.size()>0){
                tempint++;
            }
            for (int j=0;j<arrayList.size();j++){
                int num=arrayList.get(j)-tempint;
                tempint=arrayList.get(j);
                arrayList.set(j,num);
            }
            arrayLists.set(i,arrayList);
        }
        /*
         * Thrid step is combine this sheet's cells
         * and return this sheet.
         * */
        for (int i=0;i<arrayLists.size();i++){
            int start=0;
            if (sheetHeaders.size()>0){
                start=1;
            }
            ArrayList<Integer> arrayList=arrayLists.get(i);
            int col=combineColums.get(i);
            for (int j=0;j<arrayList.size();j++){
                int combinelength=arrayList.get(j);
                if (combinelength!=1){
                    sheet.mergeCells(col,start,col,start+combinelength-1);
                    start=start+combinelength-1;
                }
                start++;
            }
        }
    }

}
