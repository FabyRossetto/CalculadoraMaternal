/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.calculadoramaternal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

//fecha probable de parto:  sumando 280 días desde la fecha del primer día de la última regla.
public class CalculadoraMaternal {

    public static void main(String[] args) {

        LocalDate fechaUltimaMenstruacion;
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        LocalDate fechaProbableDeParto;
        LocalDate fechaEstimadaDeEmbarazo;
        String menu = """
                     INDIQUE LA OPCION DESEADA : 
                     1- CALCULAR LA FECHA PROBABLE DE PARTO
                     2- CALCULAR CUANDO QUEDÓ EMBARAZADA
                     3-SALIR
                                """;
        int opcion=0;

        try {
            
            //creo un objeto period de 280 dias
            Period periodo = Period.ofDays(280);

            //combino el LocalDate y LocalTime para formar una sola variable.
            LocalDateTime fechaYhora = LocalDateTime.of(fechaActual, horaActual);

            while (opcion != 3) {
                System.out.println(menu);
                Scanner Leer = new Scanner(System.in);
                opcion = Leer.nextInt();
                
                if (opcion == 1) {

                    System.out.println("Ingrese el primer dia de su ultima menstruacion separado por barras");
                    Scanner LeerFecha = new Scanner(System.in);
                    String fechaIngresada = LeerFecha.nextLine();

                    //tomo el string que ingresa por pantalla y lo transformo a LocalDate
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    fechaUltimaMenstruacion = LocalDate.parse(fechaIngresada,formatter);

                    //sumo el objeto period a la fecha de la ultima menstruacion
                    fechaProbableDeParto = fechaUltimaMenstruacion.plus(periodo);

                    //Darle formato
                    //formato con .ofPattern. Prestar atencion a las letras porque MM es mes, pero mm es minutos.
                    DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyy , hh : mm 'hs.'");

                    System.out.println("Siendo la fecha y hora actual " + fechaYhora.format(f));

                    //formato ofLocalizedDate
                    DateTimeFormatter fe = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);

                    System.out.println("Su fecha probable de parto es para el " + fechaProbableDeParto.format(fe));
                 
                } else if (opcion == 2) {
                    System.out.println("Ingrese su fecha probable de parto separada por guiones");
                    Scanner LeerFecha = new Scanner(System.in);
                    String fechaIngresada = LeerFecha.nextLine();

                    //parseo la fecha ingresada
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    fechaProbableDeParto = LocalDate.parse(fechaIngresada,formatter);

                    //le resto a la fecha probable de parto los 280 dias del obj period
                    fechaEstimadaDeEmbarazo = fechaProbableDeParto.minus(periodo);

                    //le doy formato a las fechas
                    DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_DATE;

                    System.out.println("Siendo su fecha probable de parto : " + fechaProbableDeParto.format(f));
                    DateTimeFormatter fe = DateTimeFormatter.ofPattern("'Su fecha estimada de embarazo fue : ' dd-MM-yyyy");
                    System.out.println(fechaEstimadaDeEmbarazo.format(fe));
                    
                  
                }
            }

        } catch (Exception e) {
            System.out.println("Su solicitud no se ha podido procesar");
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Gracias por utilizar nuestro sistema!");
        }
    }
}
