package com.sisvime.app.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("/views/personal/listarper")
public class ExcelViewController {

//    @Override
//    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
//                                      HttpServletResponse response) throws Exception {
//
//        response.setHeader("Content-Disposition", "attachmente; filename=\"Listado-del-personal.xls");
//        Sheet hoja = workbook.createSheet("Personal");
//
//        Row filaTitulo = hoja.createRow(0);
//        Cell celda = filaTitulo.createCell(0);
//        celda.setCellValue("LISTADO GENERAL DEL PERSONAL MEDICO FAP");
//
//        Row filaData = hoja.createRow(2);
//        String[] columnas = {"SERIE", "NOMBRES", "APELLIDO PATERNO", "APELLIDO MATERNO", "FECHA DE NACIMIENTO", "CORREO", "DIRECCION", "ESPECIALIDAD"};
//
//        for (int i = 0; i < columnas.length; i++) {
//            celda = filaData.createCell(i);
//            celda.setCellValue(columnas[i]);
//        }
//        List<Personal> listaP = (List<Personal>) model.get("personales");
//        int numfila = 3;
//        for (Personal personal : listaP) {
//            filaData = hoja.createRow(numfila);
//
//            filaData.createCell(0).setCellValue(personal.getId());
//            filaData.createCell(1).setCellValue(personal.getNombre());
//            filaData.createCell(2).setCellValue(personal.getApellidopat());
//            filaData.createCell(3).setCellValue(personal.getApellidomat());
//            filaData.createCell(4).setCellValue(personal.getFechanacimiento());
//            filaData.createCell(5).setCellValue(personal.getCorreo());
//            filaData.createCell(6).setCellValue(personal.getDireccion());
//            filaData.createCell(7).setCellValue(personal.getEspec().getNomespecialidad());
//
//            numfila++;
//        }
//    }
}
