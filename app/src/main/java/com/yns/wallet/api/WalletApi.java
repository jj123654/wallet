package com.yns.wallet.api;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class WalletApi {
    // 钱包管理

    public static String createMenomic() {
        // 实现创建助记词的逻辑
        return "web ecology matter tongue expire type still fee agent bike grit budget";
    }

    public static int getWalletCnt() {
        // 实现获取钱包数量的逻辑
        return 0;
    }

    public static String getAddressFromMenomic(String menomic, int index) {
        // 实现从助记词获取地址的逻辑
        return "";
    }

    public static WalletEntity createWalletFromMenomic(String menomic, int index, String password) {
        // 实现从助记词创建钱包的逻辑
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.address = "TPLqbpGHXZSLLRYKws6hFheRjYotNY8Xw" + index;
        return walletEntity;
    }

    public static WalletEntity createWalletFromPrivateKey(String privateKey, String password) {
        // 实现从私钥创建钱包的逻辑
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.address = "TPLqbpGHXZSLLRYKws6hFheRjYotNY8Xw0";
        return walletEntity;
    }

    public static void saveWallet(WalletEntity walletEntity) {
        // 实现保存钱包的逻辑
    }

    public static void deleteWallet(String address) {
        // 实现删除钱包的逻辑
    }

    public static boolean checkPassword(String password) {
        // 实现检查密码的逻辑
        return true;
    }

    public static WalletEntity getCurrentWallet() {
        // 实现获取当前钱包的逻辑
        WalletEntity wallet = new WalletEntity();
        wallet.name = "mock-name1";
        wallet.address = "TPLqbpGHXZSLLRYKws6hFheRjYotNY8XwF";
        wallet.keystore = "testkeystore";
        return wallet;
    }

    public static void setCurrentWallet(WalletEntity wallet){
        //实现设置当前钱包的逻辑
    }

    public static String getPrivateKeyFromKeystore(String keystore, String password) {
        // 实现从密钥存储获取私钥的逻辑
        return "36b9d9eac619154f0e270ae561ac23ea5921aed6b28ce2e996d2e80571e5a8ed";
    }

    public static void switchWallet(String address) {
        // 实现切换钱包的逻辑
    }

    public static List<WalletEntity> listAllWallet() {
        List<WalletEntity> addressList = new ArrayList<>();

        WalletEntity walletEntity1 = new WalletEntity();
        walletEntity1.address = "TPLqbpGHXZSLLRYKws6hFheRjYotNY8XwF";
        addressList.add(walletEntity1);

        WalletEntity walletEntity2 = new WalletEntity();
        walletEntity2.address = "TFDNwyqwrQd1tkqujo3GsQwkQYFea29x4y";
        addressList.add(walletEntity2);
        return addressList;
    }

    // token管理

    public static List<Token> getAllWalletToken(String walletAddress) {
        List<Token> tokenList = new ArrayList<>();

        Token yunusToken = new Token();
        yunusToken.address = "TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh";
        yunusToken.name = "YNS";
        yunusToken.imageUrl = "https://static.tronscan.org/production/upload/logo/new/TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh.png?t=1690628898608";
        tokenList.add(yunusToken);

        Token trxToken = new Token();
        trxToken.name = "TRX";
        trxToken.imageUrl = "https://static.tronscan.org/production/upload/logo/TNUC9Qb1rRpS5CbWLmNMxXBjyFoydXjWFR.png?t=1598430824415";
        tokenList.add(trxToken);

        Token usdtToken = new Token();
        usdtToken.address = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t";
        usdtToken.name = "USDT";
        usdtToken.imageUrl = "https://static.tronscan.org/production/logo/usdtlogo.png";
        tokenList.add(usdtToken);

        return tokenList;
    }

    public static Token getWalletToken(String walletAddress, String address) {
        Token yunusToken = new Token();
        yunusToken.address = "TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh";
        yunusToken.name = "YNS";
        yunusToken.imageUrl = "https://static.tronscan.org/production/upload/logo/new/TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh.png?t=1690628898608";
        yunusToken.balance = new BigDecimal("1");
        yunusToken.usd = new BigDecimal("10");
        return yunusToken;
    }

    public static void addWalletToken(String walletAddress, String address) {
        // 实现添加令牌到钱包的逻辑
    }

    public static void deleteWalletToken(String walletAddress, String address) {
        // 实现从钱包删除令牌的逻辑
    }

    public static List<Token> getPopularToken() {
        // 实现获取热门令牌的逻辑
        List<Token> tokenList = new ArrayList<>();

        Token yunusToken = new Token();
        yunusToken.address = "TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh";
        yunusToken.name = "YNS";
        yunusToken.imageUrl = "https://static.tronscan.org/production/upload/logo/new/TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh.png?t=1690628898608";
        tokenList.add(yunusToken);

        Token trxToken = new Token();
        trxToken.name = "TRX";
        trxToken.imageUrl = "https://static.tronscan.org/production/upload/logo/TNUC9Qb1rRpS5CbWLmNMxXBjyFoydXjWFR.png?t=1598430824415";
        tokenList.add(trxToken);

        Token usdtToken = new Token();
        usdtToken.address = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t";
        usdtToken.name = "USDT";
        usdtToken.imageUrl = "https://static.tronscan.org/production/logo/usdtlogo.png";
        tokenList.add(usdtToken);

        return tokenList;
    }

    public static Token getToken(String tokenAddress) {
        Token yunusToken = new Token();
        yunusToken.address = "TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh";
        yunusToken.name = "YNS";
        yunusToken.imageUrl = "https://static.tronscan.org/production/upload/logo/new/TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh.png?t=1690628898608";
        yunusToken.balance = new BigDecimal("1");
        yunusToken.usd = new BigDecimal("10");
        return yunusToken;

    }

    public enum TX_RESULT {
        SUCCESS,
        FAILED
    }

    public static List<TokenTransferTransaction> getTokenTransaction(
            String walletAddress,
            String tokenContractAddress,
            long startTime,
            int size) {
        // 实现获取令牌交易的逻辑
        return null;
    }

    // 交易管理

    public static String writeContract(
            String walletAddress,
            String privateKey,
            String contractAddress,
            String func,
            Object... args) {
        // 实现写入合同的逻辑
        return "";
    }

    public static String readContract(
            String walletAddress,
            String address,
            String func,
            Object... args) {
        // 实现读取合同的逻辑
        return "";
    }

    public static String transferTRX(
            String walletAddress,
            String privateKey,
            String targetAddress,
            BigDecimal amount) {
        // 实现TRX转账的逻辑
        return "";
    }

    public static class WalletEntity {
        public String name;
        public String address;
        public String keystore;
    }

    public static class Token {
        public String address;
        public String imageUrl;
        public String name;
        public BigDecimal balance;
        public BigDecimal usd;
    }

    public static class TokenTransferTransaction {
        public String from;
        public String to;
        public String tx;
        public long time;
        public BigDecimal amount;
        public TX_RESULT result;
    }
}
