package com.laba.solvd.parsers;

import com.laba.solvd.model.Airplane;

public interface Parser {
    Airplane parse(String xmlPath);
}
