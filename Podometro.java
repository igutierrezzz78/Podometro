/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author    Inigo Gutierrez
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double  ZANCADA_HOMBRE = 0.45;
    private final double  ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    String marca;
    double altura;
    char sexo;
    double longitudZancada;
    int totalPasosLaborables;
    int totalPasosSabado;
    int totalPasosDomingo;
    double totalDistanciaSemana;
    double totalDistanciaFinSemana;
    int tiempo;
    int caminatasNoche;


    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) 
    {
        marca = queMarca;
        altura = 0;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
        sexo = MUJER;

    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() 
    {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) 
    {
        sexo = queSexo;
        altura = queAltura;
        if(sexo == MUJER){
            longitudZancada = Math.floor(altura*ZANCADA_MUJER);
        }
        if(sexo == HOMBRE){
            longitudZancada = Math.ceil(altura*ZANCADA_HOMBRE);
        }
    }

     /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFin � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,int horaFin) 
    {
        int tiempo_caminata;
        switch (dia)
        {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5: totalPasosLaborables = totalPasosLaborables + pasos;
         break;
        case SABADO: totalPasosSabado = totalPasosSabado + pasos;
        break;
        case DOMINGO: totalPasosDomingo = totalPasosDomingo + pasos;
         break;
        }
        
        totalDistanciaSemana = (((double)totalPasosLaborables * longitudZancada)/100000);
        totalDistanciaFinSemana = ((((double)totalPasosSabado + (double)totalPasosDomingo) * (longitudZancada))/100000);
        
        if(horaFin/100 - horaInicio/100 < 0)
        {
            tiempo_caminata = (((horaFin/100 - horaInicio/100)+24)*60)+(horaFin%100 - horaInicio%100);
        }
        else
        {
            tiempo_caminata = ((horaFin/100 - horaInicio/100)*60)+(horaFin%100 - horaInicio%100);
        }
        
        if(horaInicio >= 2100)
        {
           caminatasNoche++;
        }
        
        tiempo = tiempo + tiempo_caminata;
    }
    
     /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    
    public void printConfiguracion() 
    {
        System.out.println("Configuracion del Podometro");
        System.out.println("****************************");
        System.out.println("Altura: "+ (altura/100) + " mts.");
        System.out.println("Sexo: "+ sexo);
        System.out.println("Longitud Zancada: " + (longitudZancada/100) + " mts.");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */

    public void printEstad�sticas() 
    {
        System.out.println("Estadisticas");
        System.out.println("*********************");
        System.out.println("Distancia recorrida toda la semana: "+(totalDistanciaSemana + totalDistanciaFinSemana) + " Km");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km");
        System.out.println("    ");
        System.out.println("N� pasos d�as laborables: " + totalPasosLaborables);
        System.out.println("N� pasos S�BADO: " + totalPasosSabado);
        System.out.println("N� pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("   ");
        System.out.println("N� caminatas realizadas a partir de las 21h.: " + caminatasNoche);
        System.out.println("    ");
        System.out.println("Tiempo total caminado en la semana: " + (tiempo/60)+" h." + (tiempo%60) + " min.");
        System.out.println("D�a/s con m�s pasos caminados: " + diaMayorNumeroPasos());

    }

   

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    
    public String diaMayorNumeroPasos() 
    {
          if(totalPasosSabado > totalPasosLaborables && totalPasosSabado > totalPasosDomingo)
        {
            return "SABADO";
        }  
        if( totalPasosDomingo> totalPasosLaborables && totalPasosDomingo> totalPasosSabado )
        {
            return "DOMINGO";
        }  
        if(totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo)
        {
            return "LABORALES";
        }  
        if(totalPasosSabado == totalPasosLaborables && totalPasosLaborables > totalPasosDomingo)
        {
        return "LABORALES SABADOS";
        }
        if(totalPasosSabado == totalPasosDomingo && totalPasosDomingo >totalPasosLaborables )
        {
        return "SABADOS DOMINGO";
        }
        if(totalPasosLaborables == totalPasosDomingo && totalPasosDomingo >totalPasosSabado )
        {
        return "LABORALES DOMINGO";
        }
        if(totalPasosLaborables == totalPasosDomingo && totalPasosDomingo == totalPasosSabado )
        {
        return "LABORALES DOMINGO SABADO";
        }
        return "";
         

    }
    
    
    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */ 
    
    public void reset() 
    {
        altura = 0;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
        sexo = MUJER;
    }
    

}
