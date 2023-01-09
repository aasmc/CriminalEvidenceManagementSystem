package ru.aasmc.cems.util;

import ru.aasmc.cems.dao.AbstractEntity;

import java.util.Comparator;

public class Functions {
    public static Comparator<AbstractEntity> COMPARATOR_BY_ID =
            Comparator.comparing(AbstractEntity::getId);
}
