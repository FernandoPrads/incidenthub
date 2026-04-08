CREATE TABLE pessoa (
    id_pessoa BIGSERIAL PRIMARY KEY,
    nome_completo VARCHAR(255),
    data_nascimento DATE,
    documento_cpf VARCHAR(14) UNIQUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE localizacao (
    id_localizacao SERIAL PRIMARY KEY,
    logradouro VARCHAR(255),
    bairro VARCHAR(255),
    cep VARCHAR(10),
    cidade VARCHAR(255),
    uf VARCHAR(2)
);

CREATE TABLE registro_incidente (
    id_incidente SERIAL PRIMARY KEY,
    id_pessoa INTEGER,
    id_localizacao INTEGER,
    data_registro TIMESTAMP,
    status_registro VARCHAR(20),
    CONSTRAINT fk_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
    CONSTRAINT fk_local FOREIGN KEY (id_localizacao) REFERENCES localizacao(id_localizacao)
);

CREATE TABLE midia_incidente (
    id_midia SERIAL PRIMARY KEY,
    id_incidente INTEGER,
    criado_em TIMESTAMP,
    caminho_arquivo VARCHAR(500),
    hash_arquivo VARCHAR(255),
    CONSTRAINT fk_incidente FOREIGN KEY (id_incidente) REFERENCES registro_incidente(id_incidente)
);