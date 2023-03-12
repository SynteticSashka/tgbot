CREATE TABLE client (
    chat_id            bigint    NOT NULL PRIMARY KEY,
    name               varchar   NOT NULL DEFAULT '',
    unique_code        varchar   NOT NULL DEFAULT '',
    status             varchar   NOT NULL DEFAULT '',
    created_at         timestamp NOT NULL DEFAULT now(),
    updated_at         timestamp NOT NULL DEFAULT now()
);

CREATE TABLE accentuation_progress (
    chat_id            bigint    NOT NULL PRIMARY KEY,
    current_question   int       NOT NULL DEFAULT 0,
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

CREATE SEQUENCE dictionary_seq;

CREATE TABLE dictionary (
    id                bigint NOT NULL DEFAULT nextval('dictionary_seq'),
    text              varchar,

    CONSTRAINT pk_dictionary PRIMARY KEY (id)
);