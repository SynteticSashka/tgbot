CREATE TABLE client (
    chat_id            bigint    NOT NULL PRIMARY KEY,
    name               varchar   NOT NULL DEFAULT '',
    status             int       NOT NULL DEFAULT -1,
    created_at         timestamp NOT NULL DEFAULT now(),
    updated_at         timestamp NOT NULL DEFAULT now()
);

CREATE TABLE progress (
    chat_id            bigint  NOT NULL PRIMARY KEY,
    current_question   int     NOT NULL DEFAULT 0,
    demonstr_score     int     NOT NULL DEFAULT 0,
    zastr_score        int     NOT NULL DEFAULT 0,
    pedant_score       int     NOT NULL DEFAULT 0,
    vozb_score         int     NOT NULL DEFAULT 0,
    gipertim_score     int     NOT NULL DEFAULT 0,
    distim_score       int     NOT NULL DEFAULT 0,
    trevozj_score      int     NOT NULL DEFAULT 0,
    exalt_score        int     NOT NULL DEFAULT 0,
    emotiv_score       int     NOT NULL DEFAULT 0,
    tsiklotim_score    int     NOT NULL DEFAULT 0,
    result             int
);

CREATE SEQUENCE dictionary_seq;

CREATE TABLE dictionary (
    id                bigint NOT NULL DEFAULT nextval('dictionary_seq'),
    text              varchar
);