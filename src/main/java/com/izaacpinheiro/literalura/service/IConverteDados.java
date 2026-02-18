package com.izaacpinheiro.literalura.service;

public interface IConverteDados {
    <T> T getDados(String json, Class<T> tClass);
}
