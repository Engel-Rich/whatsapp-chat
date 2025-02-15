ALTER TABLE Conversations
ADD COLUMN last_message_id BIGINT NOT NULL,
ADD CONSTRAINT last_message_conversation_id_fk FOREIGN KEY (last_message_id)  REFERENCES Messages(id);

ALTER TABLE Groupes
ADD COLUMN last_message_id BIGINT NOT NULL,
ADD CONSTRAINT last_message_group_id_fk FOREIGN KEY (last_message_id)  REFERENCES Messages(id);