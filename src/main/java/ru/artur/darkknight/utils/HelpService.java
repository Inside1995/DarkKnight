package ru.artur.darkknight.utils;

import ru.artur.darkknight.exception.ConditionNotEnoughException;
import ru.artur.darkknight.exception.GoldNotEnoughException;
import ru.artur.darkknight.exception.TrainingNotPossible;
import ru.artur.darkknight.model.Knight;
import ru.artur.darkknight.model.enums.TrainingType;

public class HelpService {
    public static void trainHero(Knight knight, String cases, TrainingType trainingType) throws GoldNotEnoughException, ConditionNotEnoughException, TrainingNotPossible {
        if (cases != null) {
            String[] split  = cases.split(",");
            int needToTrain = Integer.parseInt(split[0]);
            int condition   = Integer.parseInt(split[1]);
            int price       = Integer.parseInt(split[2]);
            knight.train(needToTrain, condition, price, trainingType);
        }
    }

    public static int getDamage(Knight knight1, Knight knight2) {
        int critDamage = (int) (Math.random() * 5) == 3 ? 10 : 0;
        int damage = Math.abs((knight1.getTotalStrength() - knight2.getTotalDefence())) / 15 + critDamage;
        return damage;
    }
}
