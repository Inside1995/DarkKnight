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
        attackActions.add("%s attacked %s with a trough and dealt %d damage");
        attackActions.add("%s poked with a fork %s inflicted %d damage");

        defenceActions.add("%s was parried worthless kick %s, but got %d points of damage");
        defenceActions.add("%s evaded %s, but slipped and hit his head. %D damage received");

        actions.put(DuelMoveType.ATTACK, attackActions);
        actions.put(DuelMoveType.DEFENCE, defenceActions);
    }

    @Override
    public String findAction(DuelMoveType type) {
        return actions.get(type).iterator().next();
    }
}
