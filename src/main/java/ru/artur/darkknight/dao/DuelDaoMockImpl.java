package ru.artur.darkknight.dao;

import org.springframework.stereotype.Repository;
import ru.artur.darkknight.model.enums.DuelMoveType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class DuelDaoMockImpl implements DuelDao {
    private static final Map<DuelMoveType, Set<String>> actions = new HashMap<>();
    private static final Set<String> attackActions  = new HashSet<>();
    private static final Set<String> defenceActions = new HashSet<>();

    static {
        attackActions.add("%s атаковал %s корытом и нанес ему %d единиц урона");
        attackActions.add("%s ткнул вилкой %s и нанес ему %d единиц урона");

        defenceActions.add("%s парировал никчемный удар %s, однако получил %d единиц урона");
        defenceActions.add("%s уклонился от удара %s, но подскользнулся и ударился головой. Получено %d единиц урона");

        actions.put(DuelMoveType.ATTACK, attackActions);
        actions.put(DuelMoveType.DEFENCE, defenceActions);
    }

    @Override
    public String findAction(DuelMoveType type) {
        return actions.get(type).iterator().next();
    }
}
