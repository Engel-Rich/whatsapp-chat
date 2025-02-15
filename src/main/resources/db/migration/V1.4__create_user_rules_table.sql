-- Etant donne qu'un utilisateur peut avoir plusieurs rules et que le meme rule peut etres attribue a plusieurs utilisateur --

CREATE TABLE IF NOT EXISTS  UsersRules
(
    user_id BIGINT NOT NULL,
    rule_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, rule_id),
    CONSTRAINT users_rules_userID_fk FOREIGN KEY (user_id) REFERENCES Users(id),
    CONSTRAINT users_rules_ruleID_fk FOREIGN KEY (rule_id) REFERENCES Rules(id)
);