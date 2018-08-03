package ru.artur.darkknight.utils;

import ru.artur.darkknight.exception.ConditionNotEnoughException;
import ru.artur.darkknight.exception.GoldNotEnoughException;
import ru.artur.darkknight.exception.TrainingNotPossible;
import ru.artur.darkknight.model.Char;
import ru.artur.darkknight.model.enums.TrainingType;

public class HelpService {
    public static void trainHero(Char aChar, String value, TrainingType trainingType) throws GoldNotEnoughException, ConditionNotEnoughException, TrainingNotPossible {
        if (value != null) {
            int needToTrain = 1;
            int condition   = 10;
            int price       = 10;
            aChar.train(needToTrain, condition, price, trainingType);
        }
    }

    /*
        Если "атака" атакующего >= "защиты" атакуемого, то:
        att_mod = 1+("атака"-"защита")*0,05.
        Если "атака" атакующего < "защиты" атакуемого, то:
        att_mod = 1/(1+("защита"-"атака")*0,05).
     */
    public static int getDamage(Char char1, Char char2) {
//        int critDamage = (int) (Math.random() * 5) == 3 ? 10 : 0;
//        int damage = Math.abs((char1.getTotalStrength() * 3 - char2.getTotalDefence())) / 15 + critDamage;
//        return damage;
        double att_mod = (char1.getTotalStrength() >= char2.getTotalDefence() ? (1 + (char1.getTotalStrength() - char2.getTotalDefence()) * 0.05) :
                (1 / (1 + (char1.getTotalStrength() - char2.getTotalDefence()) * 0.05)));
        int damage  = (int) (10 * att_mod);
        int crit    = (int) (damage * 0.5);
        damage      = (Math.random() * 9 == 2) ? damage + crit : damage;
        return damage;
    }
}
