CREATE TABLE client (
    chat_id            bigint    NOT NULL PRIMARY KEY,
    name               varchar   NOT NULL DEFAULT '',
    id                 varchar   NOT NULL DEFAULT '',
    current_state      int       NOT NULL DEFAULT 0,
    created_at         timestamp NOT NULL DEFAULT now(),
    updated_at         timestamp NOT NULL DEFAULT now()
);

CREATE TABLE accentuation_progress (
    id                 varchar   NOT NULL PRIMARY KEY,
    demonstr_score     int       NOT NULL DEFAULT 0,
    zastr_score        int       NOT NULL DEFAULT 0,
    pedant_score       int       NOT NULL DEFAULT 0,
    vozb_score         int       NOT NULL DEFAULT 0,
    gipertim_score     int       NOT NULL DEFAULT 0,
    distim_score       int       NOT NULL DEFAULT 0,
    trevozj_score      int       NOT NULL DEFAULT 0,
    exalt_score        int       NOT NULL DEFAULT 0,
    emotiv_score       int       NOT NULL DEFAULT 0,
    tsiklotim_score    int       NOT NULL DEFAULT 0,
    is_done            bool      NOT NULL DEFAULT false,
    updated_at         timestamp NOT NULL DEFAULT now()
);

CREATE TABLE accentuation_questions (
   id                bigint  NOT NULL PRIMARY KEY,
   text              varchar NOT NULL DEFAULT ''
);

CREATE TABLE accentuation_scales (
   id                                bigint  NOT NULL PRIMARY KEY,
   name                              varchar NOT NULL DEFAULT '',
   description                       varchar NOT NULL DEFAULT '',
   detailed_description              varchar NOT NULL DEFAULT ''
);

CREATE SEQUENCE state_messages_seq;

CREATE TABLE state_messages (
    state                int NOT NULL DEFAULT nextval('state_messages_seq'),
    message              varchar,

    CONSTRAINT pk_state_messages PRIMARY KEY (state)
);

CREATE SEQUENCE dictionary_seq;

CREATE TABLE dictionary (
    id                bigint NOT NULL DEFAULT nextval('dictionary_seq'),
    text              varchar,

    CONSTRAINT pk_dictionary PRIMARY KEY (id)
);