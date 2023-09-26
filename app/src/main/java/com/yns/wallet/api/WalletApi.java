package com.yns.wallet.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yns.wallet.bean.Data;
import com.yns.wallet.bean.PopularTokenInfo;
import com.yns.wallet.bean.Response;
import com.yns.wallet.bean.SearchTokenInfo;
import com.yns.wallet.bean.TokenTransferTransactionModel;
import com.yns.wallet.bean.TransactionRecordModel;
import com.yns.wallet.io.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.UUID;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class WalletApi {
    // 钱包管理

    public static String createMenomic() {
        // 实现创建助记词的逻辑
        return "web ecology matter tongue expire type still fee agent bike grit budget";
    }

    public static int getWalletCnt() {
        // 实现获取钱包数量的逻辑
        return 2;
    }

    public static String getAddressFromMenomic(String menomic, int index) {
        // 实现从助记词获取地址的逻辑
        return UUID.randomUUID().toString();
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
        return password.equals("Ks123456");
    }

    public static void changePassword(String newPassword, String oldPassword) {
        Log.i("change password", String.format("new:%s old:%s", newPassword, oldPassword));

    }

    public static String getMenomic(String password) {
        // 实现通过密码获取助记词的逻辑
        return "web ecology matter tongue expire type still fee agent bike grit budget";
    }

    public static boolean hasHDWallet() {
        return true;
    }

    public static void saveMenomic(String menomic) {
        //实现保存助记词的逻辑
    }

    public static WalletEntity getCurrentWallet() {
        // 实现获取当前钱包的逻辑
        WalletEntity wallet = new WalletEntity();
        wallet.name = "mock-name1";
        wallet.address = "TPLqbpGHXZSLLRYKws6hFheRjYotNY8XwF";
        wallet.keystore = "testkeystore";
        return wallet;
    }

    public static void setCurrentWallet(WalletEntity wallet) {
        //实现设置当前钱包的逻辑
        Log.d("setCurrentWallet", wallet.address);
    }

    public static String getPrivateKeyFromKeystore(String keystore, String password) {
        // 实现从密钥存储获取私钥的逻辑
        return "36b9d9eac619154f0e270ae561ac23ea5921aed6b28ce2e996d2e80571e5a8ed";
    }

    public static void switchWallet(String address) {
        // 实现切换钱包的逻辑
        Log.d("switchWallet", address);
    }

    public static List<WalletEntity> listAllWallet() {
        List<WalletEntity> addressList = new ArrayList<>();

        WalletEntity walletEntity1 = new WalletEntity();
        walletEntity1.address = "TPLqbpGHXZSLLRYKws6hFheRjYotNY8XwF";
        addressList.add(walletEntity1);

        WalletEntity walletEntity2 = new WalletEntity();
        walletEntity2.address = "TFDNwyqwrQd1tkqujo3GsQwkQYFea29x4y";
        addressList.add(walletEntity2);

        WalletEntity walletEntity3 = new WalletEntity();
        walletEntity3.address = "TFDNwyqwrQd1tkqujo3GsQwkQYFea29x4y213";
        addressList.add(walletEntity3);
        return addressList;
    }

    public static AccountResource getAccountResource(String address) {
        AccountResource source = new AccountResource();
        source.trx = BigDecimal.valueOf(239);
        source.bandwidth = BigDecimal.valueOf(10);
        source.energy = BigDecimal.valueOf(37);
        return source;
    }

    public static  WalletEntity getwalltbyaddress(String address)
    {
        //    todo:我简单的返回个空对象
        return new WalletEntity();
    }

    // token管理

    public static List<Token> getAllWalletToken(String walletAddress) {
        List<Token> tokenList = new ArrayList<>();

        Token yunusToken = new Token();
        yunusToken.address = "TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh";
        yunusToken.name = "YNS";
        yunusToken.imageUrl = "https://static.tronscan.org/production/upload/logo/new/TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh.png?t=1690628898608";
        yunusToken.usd = new BigDecimal("0.53");
        yunusToken.balance = new BigDecimal("75.23");
        yunusToken.trxAmount = new BigDecimal("0.41");
        yunusToken.usdPrice = new BigDecimal("0.19");
        yunusToken.preset = false;
        tokenList.add(yunusToken);

        Token trxToken = new Token();
        trxToken.address = "TKevsGkyqoSux8NENgbM1An1cLt6QQw456gfhdfbGh";
        trxToken.name = "TRX";
        trxToken.imageUrl = "https://static.tronscan.org/production/upload/logo/TNUC9Qb1rRpS5CbWLmNMxXBjyFoydXjWFR.png?t=1598430824415";
        trxToken.usd = new BigDecimal("0.14");
        trxToken.balance = new BigDecimal("23.33");
        trxToken.trxAmount = new BigDecimal("0.41");
        trxToken.usdPrice = new BigDecimal("0.19");
        trxToken.preset = true;
        tokenList.add(trxToken);

        Token usdtToken = new Token();
        usdtToken.address = "TY7copxkSQZBym6eTGMEdrqPHaNNsmjxKe";
        usdtToken.name = "USDT";
        usdtToken.imageUrl = "https://static.tronscan.org/production/logo/usdtlogo.png";
        usdtToken.usd = new BigDecimal("7.22");
        usdtToken.balance = new BigDecimal("44.44");
        usdtToken.trxAmount = new BigDecimal("0.41");
        usdtToken.usdPrice = new BigDecimal("0.19");
        usdtToken.preset = false;
        tokenList.add(usdtToken);

        return tokenList;
    }

    //暂时没用
    public static Token getWalletToken(String walletAddress, String address) {
        Token yunusToken = new Token();
        yunusToken.address = "TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh";
        yunusToken.name = "YNS";
        yunusToken.imageUrl = "https://static.tronscan.org/production/upload/logo/new/TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh.png?t=1690628898608";
        yunusToken.balance = new BigDecimal("1");
        yunusToken.usd = new BigDecimal("10");
        return yunusToken;
    }

    public static void addWalletTokenAdress(String walletAddress, String token) {
        Log.i("addWalletTokenAdress", String.format("addWalletToken wallet:%s token:%s", walletAddress, token));
    }

    public static void addWalletToken(String walletAddress, String address) {
        // 实现添加令牌到钱包的逻辑
        Log.d("addWalletToken", String.format("wallet: %s token:%s", walletAddress, address));
    }

    public static void deleteWalletToken(String walletAddress, String address) {
        // 实现从钱包删除令牌的逻辑
        Log.d("deleteWalletToken", String.format("wallet: %s token:%s", walletAddress, address));
    }

    public static List<Token> getPopularToken() {
        List<Token> tokenList = new ArrayList<>();

        Response<String> stringResponse = NetworkApi.tokenOverView("TRX");
        if (stringResponse.isSuccessful() && stringResponse.getData() != null) {

            String response = stringResponse.getData();
            PopularTokenInfo info = JsonUtils.jsonToObject(response, PopularTokenInfo.class);

            if (info != null && info.getTokens() != null && info.getTokens().size() > 0) {
                WalletApi.Token token = new WalletApi.Token();
                token.address = "TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh";
                token.imageUrl = "https://static.tronscan.org/production/upload/logo/new/TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh.png?t=1690628898608";
                token.name = "YNS";
                token.preset = true;
                tokenList.add(token);

                for (int i = 0; i < info.getTokens().size(); i++) {
                    PopularTokenInfo.TokensBean it = info.getTokens().get(i);
                    token = new WalletApi.Token();
                    token.address = it.getContractAddress();
                    token.imageUrl = it.getImgUrl();
                    token.name = it.getAbbr();
//                    token.preset = it. //后续看哪个是对应preset
                    tokenList.add(token);
                }
            }

        }

        return tokenList;

    }

    public static Token getToken(String contract) {
        Token token = new Token();

        Response<String> stringResponse = NetworkApi.searchToken(contract);
        if (stringResponse.isSuccessful() && stringResponse.getData() != null) {

            String response = stringResponse.getData();
            SearchTokenInfo info = JsonUtils.jsonToObject(response, SearchTokenInfo.class);

            if (info != null && info.getTrc20_tokens() != null && info.getTrc20_tokens().size() > 0) {
                SearchTokenInfo.Trc20TokensBean trc20TokensBean = info.getTrc20_tokens().get(0);
                token.name = trc20TokensBean.getSymbol();
                token.imageUrl = trc20TokensBean.getIcon_url();
                token.address = trc20TokensBean.getContract_address();
            }
        }

        return token;

    }

    public static SwapInfo getSwapInfoFromTokenAddress(String fromTokenAddress, String toTokenAddress, String fromTokenAmount) {
        SwapInfo swapInfo = new SwapInfo();
        swapInfo.fromToken = "USDT";
        swapInfo.toToken = "YNS";
        swapInfo.rate = (new BigDecimal("0.19"));
        swapInfo.fee = (new BigDecimal("0.03"));
        swapInfo.priceImpact = (new BigDecimal("0.4"));
        swapInfo.fromAmount = (new BigDecimal("100"));
        swapInfo.toAmount = (new BigDecimal("7.992"));
        swapInfo.minReceive = (new BigDecimal("5.992"));
        return swapInfo;
    }

    public static List<SwapRecord> listSwap(String address) {
        SwapInfo swapInfo = new SwapInfo();
        swapInfo.fromToken = "USDT";
        swapInfo.toToken = "YNS";
        swapInfo.rate = (new BigDecimal("0.19"));
        swapInfo.fee = (new BigDecimal("0.03"));
        swapInfo.priceImpact = (new BigDecimal("0.4"));
        swapInfo.fromAmount = (new BigDecimal("100"));
        swapInfo.toAmount = (new BigDecimal("7.992"));
        swapInfo.minReceive = (new BigDecimal("5.992"));

        List<SwapRecord> swapList = new ArrayList<>();
        SwapRecord swapRecord = new SwapRecord();
        swapRecord.time = (1694490305000L);
        swapRecord.result = (TX_RESULT.SUCCESS);
        swapRecord.swapInfo = (swapInfo);
        swapList.add(swapRecord);

        swapRecord = new SwapRecord();
        swapRecord.time = (1694490305000L);
        swapRecord.result = (TX_RESULT.FAILED);
        swapRecord.swapInfo = (swapInfo);
        swapList.add(swapRecord);

        return swapList;
    }

    public void saveSwap(String walletAddress, SwapRecord swapRecord) {
        Log.d(
                "saveSwap",
                String.format("address: %s, from: %s, to:%s", walletAddress, swapRecord.swapInfo.fromToken, swapRecord.swapInfo.toToken)
        );
    }

    public static CallFeeModel getFeeWithCallContractParam(CallContractParam callContractParam) {
        CallFeeModel callFeeModel = new CallFeeModel();
        callFeeModel.bandwidth = 11L;
        callFeeModel.energy = 12L;
        callFeeModel.trx = BigDecimal.valueOf(13.9);
        return callFeeModel;
    }

    public static void addBackupRecord(BackupRecord bc) {
        // 将BackupRecord对象添加到列表中
    }

    public static List<BackupRecord> listBackupRecord(String wallet) {
        List<BackupRecord> filteredRecords = new ArrayList<>();
        BackupRecord bc = new BackupRecord();
        bc.address = "TLaz9R9Z4CSZKK3mbMgouWHiSDgac5mSDL";
        bc.name = "name";
        bc.type = BACKUP_TYPE.PRIVATE_KEY;
        bc.time = System.currentTimeMillis();
        filteredRecords.add(bc);
        return filteredRecords;
    }

    public enum TX_RESULT {
        SUCCESS,
        FAILED
    }

    public enum TRANSFER_TYPE {
        TRC20,
        TRC10
    }

    public static List<TokenTransferTransaction> getTokenTransaction(
            String walletAddress,
            String tokenContractAddress,
            long startTime,
            int size,
            int type,//0全部，1转出，2转入
            boolean hide//true隐藏，false不隐藏
    ) {
        // 实现获取令牌交易的逻辑
        List<TokenTransferTransaction> tokenTransferTransactionList = new ArrayList<>();

        TokenTransferTransaction yunusToken = new TokenTransferTransaction();
        yunusToken.from = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6";
        yunusToken.to = "TPLqbpGHXZSLLRYKws6hFheRjYotNY8XwF";
        yunusToken.tx = "724a04c539634ee3082aa4c108eacd42b0826082c6a39824fbac94e210a45e75";
        yunusToken.time = 1693991784000L;
        yunusToken.amount = new BigDecimal("234.41");
        yunusToken.result = TX_RESULT.SUCCESS;
        tokenTransferTransactionList.add(yunusToken);

        yunusToken = new TokenTransferTransaction();
        yunusToken.from = "TPLqbpGHXZSLLRYKws6hFheRjYotNY8XwF";
        yunusToken.to = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6";
        yunusToken.tx = "724a04c539634ee3082aa4c108eacd42b0826082c6a39824fbac94e210a45e75";
        yunusToken.time = 1693991784000L;
        yunusToken.amount = new BigDecimal("3456.33");
        yunusToken.result = TX_RESULT.FAILED;
        tokenTransferTransactionList.add(yunusToken);

        return tokenTransferTransactionList;
    }

    public static TokenTransferTransactionDetail getTokenTransactionDetail(String tx) {
        TokenTransferTransactionDetail detail = new TokenTransferTransactionDetail();
        detail.from = ("TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t");
        detail.to = ("TPLqbpGHXZSLLRYKws6hFheRjYotNY8XwF");
        detail.tx = ("724a04c539634ee3082aa4c108eacd42b0826082c6a39824fbac94e210a45e75");
        detail.time = (1693991784000L);
        detail.result = (TX_RESULT.SUCCESS);
        detail.fee = (new BigDecimal("7.992"));
        detail.transferType = (TRANSFER_TYPE.TRC20);

        return detail;
    }

    public static List<Data> getAllTradeListWithAdress(
            String walletAddress,
            long startTime,
            int size,
            int type//0全部，1转出，2转入
    ){
        //type 暂时传0
        Response<String> r = NetworkApi.transaction(type, startTime, size, walletAddress);
        if (r.isSuccessful() && r.getData() != null) {
            TransactionRecordModel model = new Gson().fromJson(r.getData(), TransactionRecordModel.class);
            return model.getData();
        }
        return new ArrayList<>();
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
        return "724a04c539634ee3082aa4c108eacd42b0826082c6a39824fbac94e210a45e75";
    }

    public static String transfer(String tokenAddress, BigDecimal amount, String to) {
        Log.i("transfer", String.format("token: %s amount:%s to:%s", tokenAddress, amount.toString(), to));
        return "724a04c539634ee3082aa4c108eacd42b0826082c6a39824fbac94e210a45e75";
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
        public BigDecimal balance = new BigDecimal("0");
        public BigDecimal usd = new BigDecimal("0");
        public BigDecimal trxAmount = new BigDecimal("0");
        public BigDecimal usdPrice = new BigDecimal("0");
        public boolean preset;//true不显示+-号
    }

    public static class TokenTransferTransaction {
        public String from;
        public String to;
        public String tx;
        public long time;
        public BigDecimal amount;
        public TX_RESULT result;
    }

    public static class TokenTransferTransactionDetail extends TokenTransferTransaction {
        public BigDecimal fee;
        public TRANSFER_TYPE transferType;
    }

    public static class SwapInfo {
        public String fromToken;
        public String toToken;
        public BigDecimal rate;
        public BigDecimal fee;
        public BigDecimal priceImpact;
        public BigDecimal fromAmount;
        public BigDecimal toAmount;
        public BigDecimal minReceive;
    }

    public static class SwapRecord {
        public long time;
        public TX_RESULT result;
        public SwapInfo swapInfo;
    }

    // 这个作为事件参数，弹出交易确认框
    public static class CallContractParam {
        public String caller;
        public String address;
        public String func;
        public List<String> args;
    }

    public static class AccountResource {
        public BigDecimal trx;
        public BigDecimal bandwidth;
        public BigDecimal energy;
    }

    public static class CallFeeModel {
        public BigDecimal trx;
        public long bandwidth;
        public long energy;
    }

    public static class BackupRecord {
        public BACKUP_TYPE type;
        public String name;
        public String address;
        public long time;
    }

    // 枚举定义
    public enum BACKUP_TYPE {
        PRIVATE_KEY,
        MENOMIC
    }


}
