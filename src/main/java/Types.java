
import java.util.Arrays;
import java.util.List;

class Types {

    /** 
     * Checks if the move will have no effect on the opposing pokemon by checking types
     * 
     * @Param the move type as a string
     * @param the inputed oppontent type as a string
     * 
     * @return true if the types match up otherwise return false
     * 
    */
    public static boolean hasNoEffect(String userMoveType, String oppMonType) {

        //checks each case where a move will have no effect
        switch (userMoveType) {

            case "Normal":
                if (oppMonType == "Ghost") {
                    return true;
                } else
                    return false;

            case "Electric":
                if (oppMonType == "Ground") {
                    return true;
                } else
                    return false;

            case "Fighting":
                if (oppMonType == "Ghost") {
                    return true;
                } else
                    return false;

            case "Poison":
                if (oppMonType == "Steel") {
                    return true;
                } else
                    return false;

            case "Ground":
                if (oppMonType == "Flying") {
                    return true;
                } else
                    return false;

            case "Psychic":
                if (oppMonType == "Dark") {
                    return true;
                } else
                    return false;

            case "Ghost":
                if (oppMonType == "Normal") {
                    return true;
                } else
                    return false;

            case "Dragon":
                if (oppMonType == "Fairy") {
                    return true;
                } else
                    return false;

            default:
                return false;
        }
    }

    /** 
     * Checks if the move will not be very effective against the opposing pokemon
     * Takes the list based on used move type and checks for opposing mon type
     * 
     * @Param the move type as a string
     * @param the inputed oppontent type as a string
     * 
     * @return true if the list contains the opponent type otherwise return false
     * 
    */
    public static boolean notVeryEffective(String userMoveType, String oppMonType) {

        //Lists of types that resist the the type the list is named after
        List<String> normalNonEff = Arrays.asList("Rock", "Steel");
        List<String> fireNonEff = Arrays.asList("Fire", "Water", "Rock", "Dragon");
        List<String> waterNonEff = Arrays.asList("Water", "Grass", "Dragon");
        List<String> grassNonEff = Arrays.asList("Fire", "Grass", "Poison", "Flying", "Bug", "Dragon",
                "Steel");
        List<String> electricNonEff = Arrays.asList("Electric", "Grass", "Dragon");
        List<String> iceNonEff = Arrays.asList("Fire", "Water", "Ice", "Steel");
        List<String> fightingNonEff = Arrays.asList("Poison", "Flying", "Psychic", "Bug", "Fairy");
        List<String> poisonNonEff = Arrays.asList("Poison", "Ground", "Rock", "Ghost");
        List<String> groundNonEff = Arrays.asList("Grass", "Bug");
        List<String> flyingNonEff = Arrays.asList("Electric", "Rock", "Steel");
        List<String> psychicNonEff = Arrays.asList("Psychic", "Steel");
        List<String> bugNonEff = Arrays.asList("Fire", "Fighting", "Poison", "Flying", "Ghost", "Steel",
                "Fairy");
        List<String> rockNonEff = Arrays.asList("Fighting", "Ground", "Steel");
        List<String> ghostNonEff = Arrays.asList("Dragon");
        List<String> darkNonEff = Arrays.asList("Fighting", "Dragon", "Fairy");
        List<String> dragonNonEff = Arrays.asList("Steel");
        List<String> steelNonEff = Arrays.asList("Fire", "Water", "Electric", "Steel");
        List<String> fairyNonEff = Arrays.asList("Fire", "Poison", "Steel");

        //checks each type case for resistances using the lists created
        switch (userMoveType) {

            //checks the case for userMoveType
            case "Normal":
                //check if userMoveType is non-effective against oppMonType by checking list 
                if (normalNonEff.contains(oppMonType)) {
                    //return true if it is
                    return true;
                } else
                     //otherwise return false
                    return false;
            case "Fire":
                if (fireNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Water":
                if (waterNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Grass":
                if (grassNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Electric":
                if (electricNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Ice":
                if (iceNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Fighting":
                if (fightingNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Poison":
                if (poisonNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Ground":
                if (groundNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Flying":
                if (flyingNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Psychic":
                if (psychicNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Bug":
                if (bugNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Rock":
                if (rockNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Ghost":
                if (ghostNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Dark":
                if (darkNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Dragon":
                if (dragonNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Steel":
                if (steelNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Fairy":
                if (fairyNonEff.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            default:
                return false;
        }
    }
    /** 
     * Checks if the move will be super effective against the opposing pokemon
     * Takes the list based on used move type and checks for opposing mon type
     * 
     * @Param the move type as a string
     * @param the inputed oppontent type as a string
     * 
     * @return true if the list contains the opponent type otherwise return false
     * 
    */
    public static boolean superEffective(String userMoveType, String oppMonType) {

        // //Lists of types that types are super effective against
        List<String> fireEffective = Arrays.asList("Grass", "Ice", "Bug", "Steel");
        List<String> waterEffective = Arrays.asList("Fire", "Ground", "Rock");
        List<String> grassEffective = Arrays.asList("Water", "Ground", "Rock");
        List<String> electricEffective = Arrays.asList("Water", "Flying");
        List<String> iceEffective = Arrays.asList("Grass", "Ground", "Flying", "Dragon");
        List<String> fightingEffective = Arrays.asList("Normal", "Ice", "Rock", "Dark", "Steel");
        List<String> poisonEffective = Arrays.asList("Grass", "Fairy");
        List<String> groundEffective = Arrays.asList("Fire", "Electric", "Poison", "Rock", "Steel");
        List<String> flyingEffective = Arrays.asList("Grass", "Fighting", "Bug");
        List<String> psychicEffective = Arrays.asList("Fighting", "Poison");
        List<String> bugEffective = Arrays.asList("Grass", "Psychic", "Dark");
        List<String> rockEffective = Arrays.asList("Fire", "Ice", "Flying", "Bug");
        List<String> ghostEffective = Arrays.asList("Psychic", "Ghost");
        List<String> darkEffective = Arrays.asList("Psychic", "Ghost");
        List<String> dragonEffective = Arrays.asList("Dragon");
        List<String> steelEffective = Arrays.asList("Ice", "Rock", "Fairy");
        List<String> fairyEffective = Arrays.asList("Fighting", "Ghost", "Dragon");

        //checks each type case for effectiveness using the lists created
        switch (userMoveType) {
            //checks the case for userMoveType
            case "Fire":
                //check if userMoveType is effective against oppMonType by checking list 
                if (fireEffective.contains(oppMonType)) {
                    //return true if it is
                    return true;
                } else
                    //otherwise return false
                    return false;
            case "Water":
                if (waterEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Grass":
                if (grassEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Electric":
                if (electricEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Ice":
                if (iceEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Fighting":
                if (fightingEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Poison":
                if (poisonEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Ground":
                if (groundEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Flying":
                if (flyingEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Psychic":
                if (psychicEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Bug":
                if (bugEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Rock":
                if (rockEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Ghost":
                if (ghostEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Dark":
                if (darkEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Dragon":
                if (dragonEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Steel":
                if (steelEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            case "Fairy":
                if (fairyEffective.contains(oppMonType)) {
                    return true;
                } else
                    return false;
            default:
                return false;
        }
    }
}