package com.reaier.engking.service;

import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.dictionary.EnToCn;

public interface EnToCnService {
    EnToCn insert(String word, Language source);
}
