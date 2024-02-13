import java.util.Arrays;
import java.util.List;




public class MoveChoices extends Move {

    //negative heal factors mean the monster's health is lost
    //positive heal factors mean the monster's health is gained
    //stat boosts between 0 and 1 mean stats are lowered
    //stat boosts greater than 1 mean stats increase

    public MoveChoices(String moveName, String type, int damage, double healFactor, double attackBoost, double defenceBoost,
            double speedBoost, boolean shielded, boolean alwaysFirst, String typeImage) {
        super(moveName, type, damage, healFactor, attackBoost, defenceBoost, speedBoost, shielded, alwaysFirst, typeImage);
    }

    public MoveChoices (String moveName, String type, double healFactor, boolean poison, boolean burn, boolean sleep, String typeImage) {
        super(moveName, type, healFactor, poison, burn, sleep, typeImage);
    }

    /**
     * Returns a list of Move objects that represent the monster's possible moves
     * that it can use during battle. Different monsters have different move choices,
     * so the moves in the list depend on the name of the monster that is passed into
     * the method.
     * 
     * @param monName - The name of the monster
     * @return - List of Move objects representing the monster's possible moves 
     * that it can use during battle
     */
    public static List<Move> getMoveCombo(String monName) {
        
        // Declares a list of Move objects
        List<Move> moveList;
        
        //Switch statement with different cases depending on the monster's name
        switch(monName){

            //If the monster's name is "Alakazam":
            case "Alakazam" : 
                //Instantiates four Move objects which are available for Alakazam and adds them to moveList
                Move Psychic = new Move("Psychic", "Psychic", 90, 0, 1, 1, 1, false, false, "psychicType");
                Move ShadowBall = new Move("Shadow ball", "Ghost", 80, 0, 1, 1, 1, false, false, "ghostType");
                Move Toxic = new Move("Toxic", "Poison",  0, true, false, false, "PoisonType");
                Move FocusBlast = new Move("Focus Blast", "Fighting", 120, 0, 1, 0.66, 1, false, false, "fightingType" );
                moveList = Arrays.asList(Psychic, ShadowBall, Toxic, FocusBlast);

                //returns the list containing a unique combination of move objects
                return moveList;

            //If the monster's name is "Blastoise":
            case "Blastoise" :
                //Instantiates four move objects which are available for Blastoise and adds them to moveList
                Move HydroPump = new Move("Hydro Pump", "Water", 120, 0, 1, 1, 1, false, false, "waterType");
                Move IceBeam = new Move("Ice Beam", "Ice", 90, 0, 1, 1, 1, false, false, "iceType");
                Move BodySlam = new Move("Body Slam", "Normal", 85, 0, 1, 1, 1, false, false, "normalType");
                Move FlashCannon = new Move("Flash Cannon", "Steel", 80, 0, 1, 1, 1, false, false, "steelType");
                moveList = Arrays.asList(HydroPump, IceBeam, BodySlam, FlashCannon);

                //returns the list containing a unique combination of move objects
                return moveList;
            
            //If the monster's name is "Breloom":
            case "Breloom" :
                // Instantiates four Move objects which are available for Breloom and adds them to moveList
                Move CloseCombat = new Move("Close Combat", "Fighting", 120, 0, 1, 0.66, 1, false, false, "fightingType");
                Move RazorLeaf = new Move("Razor Leaf", "Grass", 65, 0, 1, 1, 1, false, false, "grassType");
                Move SwordsDance = new Move("Swords Dance", "Normal",  0, 0, 2, 1, 1, false, false, "normalType");
                Move SkyUppercut = new Move("Sky Uppercut", "Fighting",  85, 0, 1, 1, 1, false, false, "fightingType");
                moveList = Arrays.asList(CloseCombat, RazorLeaf, SwordsDance, SkyUppercut);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Charizard":
            case "Charizard" :
                // Instantiates four Move objects which are available for Charizard and adds them to moveList
                Move Flamethrower = new Move("Flamethrower", "Fire", 95, 0, 1, 1, 1, false, false, "fireType");
                Move BraveBird = new Move("Brave Bird", "Flying", 120, -0.25, 1, 1, 1, false, false, "flyingType");
                Move Roost = new Move("Roost", "Flying", 0.5, false, false, false, "flyingType");
                Move DragonClaw = new Move("Dragon Claw", "Dragon", 80, 0, 1, 1, 1, false, false, "dragonType");
                moveList = Arrays.asList(Flamethrower, BraveBird, Roost, DragonClaw);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Crustle":
            case "Crustle" :
                // Instantiates four Move objects which are available for Crustle and adds them to moveList
                Move StoneEdge = new Move("Stone Edge", "Rock", 100, 0, 1, 1, 1, false, false, "rockType");
                Move ShadowClaw = new Move("Shadow Claw", "Ghost", 70, 0, 1, 1, 1, false, false, "ghostType");
                Move Protect = new Move("Protect", "Normal", 0, 0, 1, 1, 1, true, true, "normalType");
                Move XScissor = new Move("X-Scissor", "Bug", 80, 0, 1, 1, 1, false, false, "bugType");
                moveList = Arrays.asList(StoneEdge, ShadowClaw, Protect, XScissor);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Electivire":
            case "Electivire" :
                // Instantiates four Move objects which are available for Electivire and adds them to moveList
                Move ThunderBolt = new Move("Thunderbolt", "Electric", 90, 0, 1, 1, 1, false, false, "electricType");
                CloseCombat = new Move("Close Combat", "Fighitng", 120, 0, 1, 0.66, 1, false, false, "fightingType");
                Move FirePunch = new Move("Fire Punch", "Fire", 75, 0, 1, 1, 1, false, false, "fireType");
                Move IcePunch = new Move("Ice Punch", "Ice", 75, 0, 1, 1, 1, false, false, "iceType");
                moveList = Arrays.asList(ThunderBolt, CloseCombat, FirePunch, IcePunch);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Froslass":
            case "Froslass" :
                // Instantiates four Move objects which are available for Froslass and adds them to moveList
                IceBeam = new Move("Ice Beam", "Ice", 90, 0, 1, 1, 1, false, false, "iceType");
                ShadowBall = new Move("Shadow ball", "Ghost", 80, 0, 1, 1, 1, false, false, "ghostType");
                Move Blizzard = new Move("Blizzard", "Ice", 110, 0, 1, 1, 1, false, false, "iceType");
                Move WillOWisp = new Move("Will-O-Wisp", "Fire", 0, false, true, false, "fireType");
                moveList = Arrays.asList(IceBeam, ShadowBall, Blizzard, WillOWisp);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Gardevoir":
            case "Gardevoir" :
                // Instantiates four Move objects which are available for Gardevoir and adds them to moveList
                Psychic = new Move("Psychic", "Psychic", 90, 0, 1, 1, 1, false, false, "psychicType");
                Move Moonblast = new Move("Moonblast", "Fairy", 95, 0, 1, 1, 1, false, false, "fairyType");
                WillOWisp = new Move("Will-O-Wisp", "Fire", 0, false, true, false, "fireType");
                ShadowBall = new Move("Shadow ball", "Ghost", 80, 0, 1, 1, 1, false, false, "ghostType");
                moveList = Arrays.asList(Psychic, Moonblast, WillOWisp, ShadowBall);
                
                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Gengar":
            case "Gengar" :
                // Instantiates four Move objects which are available for Gengar and adds them to moveList
                ShadowBall = new Move("Shadow ball", "Ghost", 80, 0, 1, 1, 1, false, false, "ghostType");
                Toxic = new Move("Toxic", "Poison",  0, true, false, false, "poisonType");
                Move SludgeWave = new Move("Sludge Wave", "Poison", 95, 0, 1, 1, 1, false, false, "poisonType");
                ThunderBolt = new Move("Thunderbolt", "Electric", 90, 0, 1, 1, 1, false, false, "electricType");
                moveList = Arrays.asList(ShadowBall, Toxic, SludgeWave, ThunderBolt);

                // returns the list containing a unique combination of move objects
                return moveList; 

            // If the monster's name is "Grimmsnarl":
            case "Grimmsnarl" :
                // Instantiates four Move objects which are available for Grimmsnarl and adds them to moveList
                Moonblast = new Move("Moonblast", "Fairy", 95, 0, 1, 1, 1, false, false, "fairyType");
                Move Rest = new Move("Rest", "Normal", 1, false, false, true, "normalType");
                Move BrickBreak = new Move("Brick Break", "Fighting", 75, 0, 1, 1, 1, false, false, "fightingType");
                Move DarkestLariat = new Move("Darkest Lariat", "Dark", 85, 0, 1, 1, 1, false, false, "darkType");
                moveList = Arrays.asList(Moonblast, Rest, BrickBreak, DarkestLariat);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Lucario":
            case "Lucario" :
                // Instantiates four Move objects which are available for Lucario and adds them to moveList
                CloseCombat = new Move("Close Combat", "Fighting", 120, 0, 1, 0.66, 1, false, false, "fightingType");
                Move ExtremeSpeed = new Move("ExtremeSpeed", "Normal", 80, 0, 1, 1, 1, false, true, "normalType");
                Move MeteorMash = new Move("MeteorMash", "Steel", 90, 0, 1, 1, 1, false, false, "steelType");
                IcePunch = new Move("Ice Punch", "Ice", 75, 0, 1, 1, 1, false, false, "iceType");
                moveList = Arrays.asList(CloseCombat, ExtremeSpeed, MeteorMash, IcePunch);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Mamoswine":
            case "Mamoswine" :
                // Instantiates four Move objects which are available for Mamoswine and adds them to moveList
                Move Earthquake = new Move("Earthquake", "Ground", 100, 0, 1, 1, 1, false, false, "groundType");
                IceBeam = new Move("Ice Beam", "Ice", 90, 0, 1, 1, 1, false, false, "iceType");
                BodySlam = new Move("Body Slam", "Normal", 85, 0, 1, 1, 1, false, false, "normalType");
                Move AncientPower = new Move("Ancient Power", "Rock", 60, 0, 1, 1, 1, false, false, "rockType");
                moveList = Arrays.asList(Earthquake, IceBeam, BodySlam, AncientPower);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Pidgeot":
            case "Pidgeot" :
                // Instantiates four Move objects which are available for Pidgeot and adds them to moveList
                Move Agility = new Move("Agility", "Psychic", 0, 0, 1, 1, 2, false, false, "psychicType");
                Move SteelWing = new Move("Steel Wing", "Steel", 70, 0, 1, 1, 1, false, false, "steelType");
                BraveBird = new Move("Brave Bird", "Flying", 120, -0.25, 1, 1, 1, false, false, "flyingType");
                ExtremeSpeed = new Move("ExtremeSpeed", "Normal", 80, 0, 1, 1, 1, false, true, "normalType");
                moveList = Arrays.asList(Agility, SteelWing, BraveBird, ExtremeSpeed);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Sharpedo":
            case "Sharpedo" :
                // Instantiates four Move objects which are available for Sharpedo and adds them to moveList
                Move AquaJet = new Move("Aqua Jet", "Water", 40, 0, 1, 1, 1, false, true, "waterType");
                Move Crunch = new Move("Crunch", "Dark", 80, 0, 1, 1, 1, false, false, "darkType");
                Move IceFang = new Move("Ice Fang", "Ice", 65, 0, 1, 1, 1, false, false, "iceType");
                Move PoisonFang = new Move("Poison Fang", "Poison", 50, 0, 1, 1, 1, false, false, "poisonType");
                moveList = Arrays.asList(AquaJet, Crunch, IceFang, PoisonFang);

                // returns the list containing a unique combination of move objects
                return moveList;
            
            // If the monster's name is "Snorlax":
            case "Snorlax" :
                // Instantiates four Move objects which are available for Snorlax and adds them to moveList
                BodySlam = new Move("Body Slam", "Normal", 85, 0, 1, 1, 1, false, false, "normalType");
                Earthquake = new Move("Earthquake", "Ground", 100, 0, 1, 1, 1, false, false, "groundType");
                Rest = new Move("Rest", "Normal", 1, false, false, true, "normalType");
                Move HammerArm = new Move("Hammer Arm", "Fighting", 100, 0, 1, 1, 1, false, false, "fightingType");
                moveList = Arrays.asList(BodySlam, Earthquake, Rest, HammerArm);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Steelix":
            case "Steelix" :
                // Instantiates four Move objects which are available for Steelix and adds them to moveList
                Earthquake = new Move("Earthquake", "Ground", 100, 0, 1, 1, 1, false, false, "groundType");
                Move RockSlide = new Move("Rock Slide", "Rock", 75, 0, 1, 1, 1, false, false, "rockType");
                Crunch = new Move("Crunch", "Dark", 80, 0, 1, 1, 1, false, false, "darkType");
                Move IronTail = new Move("Iron Tail", "Steel", 100, 0, 1, 1, 1, false, false, "steelType");
                moveList = Arrays.asList(Earthquake, RockSlide, Crunch, IronTail);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Turtonator":
            case "Turtonator" :
                // Instantiates four Move objects which are available for Turtonator and adds them to moveList
                Flamethrower = new Move("Flamethrower", "Fire", 95, 0, 1, 1, 1, false, false, "fireType");
                DragonClaw = new Move("Dragon Claw", "Dragon", 80, 0, 1, 1, 1, false, false, "dragonType");
                StoneEdge = new Move("Stone Edge", "Rock", 100, 0, 1, 1, 1, false, false, "rockType");
                Toxic = new Move("Toxic", "Poison", 0, true, false, false, "poisonType");
                moveList = Arrays.asList(Flamethrower, DragonClaw, StoneEdge, Toxic);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Tyrantrum":
            case "Tyrantrum" :
                // Instantiates four Move objects which are available for Tyrantrum and adds them to moveList
                DragonClaw = new Move("Dragon Claw", "Dragon", 80, 0, 1, 1, 1, false, false, "dragonType");
                Crunch = new Move("Crunch", "Dark", 80, 0, 1, 1, 1, false, false, "darkType");
                RockSlide = new Move("Rock Slide", "Rock", 75, 0, 1, 1, 1, false, false, "rockType");
                Earthquake = new Move("Earthquake", "Ground", 100, 0, 1, 1, 1, false, false, "groundType");
                moveList = Arrays.asList(DragonClaw, Crunch, RockSlide, Earthquake);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Venusaur":
            case "Venusaur" :
                // Instantiates four Move objects which are available for Venusaur and adds them to moveList
                Move SludgeBomb = new Move("Sludge Bomb", "Poison", 90, 0, 1, 1, 1, false, false, "poisonType");
                RazorLeaf = new Move("Razor Leaf", "Grass", 65, 0, 1, 1, 1, false, false, "grassType");
                Toxic = new Move("Toxic", "Poison", 0, true, false, false, "poisonType");
                Move Synthesis = new Move("Synthesis", "Grass", 0.5, false, false, false, "grassType");
                moveList = Arrays.asList(SludgeBomb, RazorLeaf, Toxic, Synthesis);

                // returns the list containing a unique combination of move objects
                return moveList;

            // If the monster's name is "Vikavolt":
            case "Vikavolt" :
                // Instantiates four Move objects which are available for Vikavolt and adds them to moveList
                ThunderBolt = new Move("Thunderbolt", "Electric", 90, 0, 1, 1, 1, false, false, "electricType");
                XScissor = new Move("X-Scissor", "Bug", 80, 0, 1, 1, 1, false, false, "bugType");
                Move EnergyBall = new Move("Energy Ball", "Grass", 80, 0, 1, 1, 1, false, false, "grassType");
                Move AirSlash = new Move("Air Slash", "Flying", 75, 0, 1, 1, 1, false, false, "flyingType");
                moveList = Arrays.asList(ThunderBolt, XScissor, EnergyBall, AirSlash);

                // returns the list containing a unique combination of move objects
                return moveList;

            //If no cases are met, return null
            default: return null;

        }
    }
}
