CREATE SCHEMA IF NOT EXISTS "pm";

CREATE TABLE IF NOT EXISTS pm.player_profiles
(
    player_id          UUID PRIMARY KEY NOT NULL,
    credential         VARCHAR,
    created            TIMESTAMP,
    modified           TIMESTAMP,
    last_session       TIMESTAMP,
    total_spent        INTEGER,
    total_refund       INTEGER,
    total_transactions INTEGER,
    last_purchase      TIMESTAMP,
    level              INTEGER,
    xp                 INTEGER,
    total_playtime     INTEGER,
    country            VARCHAR,
    language           VARCHAR,
    birthdate          TIMESTAMP,
    gender             VARCHAR,
    _customfield       VARCHAR,
    active_campaigns   VARCHAR[],
    device_id          BIGINT,
    inventory          jsonb,
    clan_id            VARCHAR


);

CREATE TABLE IF NOT EXISTS pm.clan
(
    player_id UUID NOT NULL,
    id        VARCHAR PRIMARY KEY,
    name      VARCHAR
);



CREATE TABLE IF NOT EXISTS pm.device
(
    id        BIGINT PRIMARY KEY,
    player_id UUID NOT NULL,
    model     VARCHAR,
    carrier   VARCHAR,
    firmware  VARCHAR
);

CREATE INDEX IF NOT EXISTS idx_fk_clan_id ON pm.player_profiles (clan_id);

INSERT INTO pm.clan(player_id, id, name)
VALUES ('97983be2-98b8-11e7-90cf-082e5f28d836', '123456', 'myclan');

INSERT INTO pm.device(id, player_id, model, carrier, firmware)
VALUES (123456, '97983be2-98b8-11e7-90cf-082e5f28d836', 'iPhone 12', 'AT&T', '14.3');

INSERT INTO pm.player_profiles (player_id,
                                credential,
                                created,
                                modified,
                                last_session,
                                total_spent,
                                total_refund,
                                total_transactions,
                                last_purchase,
                                level,
                                xp,
                                total_playtime,
                                country,
                                language,
                                birthdate,
                                gender,
                                active_campaigns,
                                inventory,
                                device_id,
                                clan_id,
                                _customfield)
VALUES ('97983be2-98b8-11e7-90cf-082e5f28d836',
        'apple_credential',
        '2021-01-10 13:37:17',
        '2021-01-23 13:37:17',
        '2021-01-23 13:37:17',
        400,
        0,
        5,
        '2021-01-22 13:37:17',
        3,
        1000,
        144,
        'CA',
        'fr',
        '2000-01-10 13:37:17',
        'male',
        array[]::varchar[],
        '{
          "cash": 123,
          "coins": 123,
          "item_1": 1,
          "item_34": 3,
          "item_55": 2
        }',
        '123456',
        '123456',
        'customfield');
ALTER TABLE pm.player_profiles
    ADD CONSTRAINT fk_clan FOREIGN KEY (clan_id) REFERENCES pm.clan (id);

ALTER TABLE pm.player_profiles
    ADD CONSTRAINT fk_device FOREIGN KEY (device_id) REFERENCES pm.device (id);
ALTER TABLE pm.clan
    ADD CONSTRAINT fk_player_profile FOREIGN KEY (player_id) REFERENCES pm.player_profiles (player_id);
ALTER TABLE pm.device
    ADD CONSTRAINT fk_player_profile FOREIGN KEY (player_id) REFERENCES pm.player_profiles (player_id);
