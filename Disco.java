package clases;

public class Disco
{
    //Atributos
    private String titulo = "";
	private String iDD = "";
    public String interprete = ""; 
    private String idiomas = "";

    //Constructor
    public Disco(String atributoUno, String atributoDos, String atributoTres, String atributoCuatro) 
    {
        titulo = atributoUno;
        interprete = atributoDos;
        iDD = atributoTres;
        idiomas = atributoCuatro;
    }

    //Métodos
    public String getTitulo(Disco d)
    {        
        return d.titulo;
    }
    
    public String getIDD(Disco d)
    {        
        return d.iDD;
    }

    public String getInterprete(Disco d)
    {
        return d.interprete;
    }

    public String getIdiomas(Disco d)
    {        
        return d.idiomas;
    }    
}//Fin clase Disco