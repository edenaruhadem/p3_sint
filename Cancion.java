package clases;


public class Cancion 
{
    //Atributos
    public String titulo = "";
	private String iDC = "";
    private String genero = ""; 
    public String duracion = "";
    private String desc = "";
    private String premios = "";

    //Constructor
    public Cancion(String atributoUno, String atributoDos, String atributoTres, String atributoCuatro, String atributoCinco, String atributoSeis) 
    {
        titulo = atributoUno;
        genero = atributoDos;
        duracion = atributoTres;
        iDC = atributoCuatro;
        desc = atributoCinco;
        premios = atributoSeis;        
    }

    //Métodos
    public String getTitulo(Cancion c)
    {        
        return c.titulo;
    }    
    public String getGenero(Cancion c)
    {
        return c.genero;
    }
    public String getDuracion(Cancion c)
    {        
        return c.duracion;
    }
    public String getIdc(Cancion c)
    {        
        return c.iDC;
    }
    public String getDescripcion(Cancion c)
    {        
        return c.desc;
    }
    public String getPremios(Cancion c)
    {      
        return c.premios;
    }
}