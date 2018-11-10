package controlStructures.controlStructuresEnum;

public class ControlStructures {

    public  void printBird(Birds bird) {

        switch (bird) {
            case OREL:
                System.out.println("OREL is bird");
                break;
            case SINICA:
                System.out.println("SINICA is bird");
                break;
            case SOROKA:
                System.out.println("SOROKA is bird");
                break;
            case VORONA:
                System.out.println("VORONA is bird");
                break;
            case POPUGAI:
                System.out.println("POPUGAI is bird");
                break;
        }


    }

    public void ifElseIf(Enum bird){
        if(bird == Birds.OREL){
            System.out.println("OREL is bird");
        }
        else if(bird == Birds.SINICA){
            System.out.println("SINICA is bird");
        }
        else if(bird == Birds.SOROKA){
            System.out.println("SOROKA is bird");
        }
        else if(bird == Birds.VORONA){
            System.out.println("VORONA is bird");
        }
        else if(bird == Birds.POPUGAI){
            System.out.println("POPUGAI is bird");
        }
    }

  }





