CREATE TABLE IF NOT EXISTS Messages(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    sender_conversation_id BIGINT,
    dest_conversation_id BIGINT,
    sender_id BIGINT NOT NULL,
    groupe_id BIGINT,
    message TEXT,
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    message_type ENUM("TEXT","MULTI_IMAGES", "STiKER", "IMAGE","CODE") NOT NULL DEFAULT "TEXT",
    created_at  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT sender_conversation_id_fk FOREIGN KEY  (sender_conversation_id) REFERENCES Conversations(id) ON DELETE CASCADE,
    CONSTRAINT dest_conversation_id_fk FOREIGN KEY  (dest_conversation_id) REFERENCES Conversations(id)  ON DELETE CASCADE,
    CONSTRAINT sender_id_fk FOREIGN KEY  (sender_id) REFERENCES Users(id),
    CONSTRAINT groupe_id_fk FOREIGN KEY  (groupe_id) REFERENCES Groupes(id)
);