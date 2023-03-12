CREATE TABLE client (
    chat_id            bigint    NOT NULL PRIMARY KEY,
    name               varchar   NOT NULL DEFAULT '',
    status             int       NOT NULL DEFAULT -1,
    created_at         timestamp NOT NULL DEFAULT now(),
    updated_at         timestamp NOT NULL DEFAULT now()
);

CREATE TABLE accentuation_progress (
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
    is_done            bool    NOT NULL DEFAULT false
);

CREATE SEQUENCE questions_seq;

CREATE TABLE questions (
   id                bigint  NOT NULL DEFAULT nextval('questions_seq'),
   text              varchar NOT NULL DEFAULT ''
);

CREATE SEQUENCE results_seq;

CREATE TABLE results (
   id                bigint  NOT NULL DEFAULT nextval('results_seq'),
   text              varchar NOT NULL DEFAULT ''
);

CREATE SEQUENCE dictionary_seq;

CREATE TABLE dictionary (
    id                bigint NOT NULL DEFAULT nextval('dictionary_seq'),
    text              varchar
);