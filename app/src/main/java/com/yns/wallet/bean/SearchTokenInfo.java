package com.yns.wallet.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchTokenInfo {

    private List<Trc20TokensBean> trc20_tokens;

    public List<Trc20TokensBean> getTrc20_tokens() {
        return trc20_tokens;
    }

    public void setTrc20_tokens(List<Trc20TokensBean> trc20_tokens) {
        this.trc20_tokens = trc20_tokens;
    }

    public static class Trc20TokensBean {
        private String symbol;
        private String icon_url;
        private String contract_address;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public String getContract_address() {
            return contract_address;
        }

        public void setContract_address(String contract_address) {
            this.contract_address = contract_address;
        }
    }
}
