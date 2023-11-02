
import lombok.Getter;
import java.util.List;

/*----------------------------------------------------------------
Usamos Lombok para los Getters, en mi caso no me funcionaban y tuve que instalar el
plugin de Lombok en mi IDE, probar esa solución si a vos no te está funcionando.
---------------------------------------------------------------------*/
@Getter
public class CharacterPOJO {

        public String skinColor;
        public List<String> films;

    public CharacterPOJO(String skinColor, List<String> films) {
        this.skinColor = skinColor;
        this.films = films;
    }

    public CharacterPOJO() {
    }
}