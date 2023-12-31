package com.yns.wallet.bean;

import java.util.List;

public class PopularTokenInfo {


    private Integer all;
    private Integer currentWeekAll;
    private Integer currentWeekTotalAll;
    private List<TokensBean> tokens;
    private Integer total;
    private Integer total10;
    private Integer total1155;
    private Integer total20;
    private Integer total721;
    private Integer totalAll;
    private Integer totalAllCount;
    private Long updateTime;
    private Integer valueAtLeast;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Integer getCurrentWeekAll() {
        return currentWeekAll;
    }

    public void setCurrentWeekAll(Integer currentWeekAll) {
        this.currentWeekAll = currentWeekAll;
    }

    public Integer getCurrentWeekTotalAll() {
        return currentWeekTotalAll;
    }

    public void setCurrentWeekTotalAll(Integer currentWeekTotalAll) {
        this.currentWeekTotalAll = currentWeekTotalAll;
    }

    public List<TokensBean> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokensBean> tokens) {
        this.tokens = tokens;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal10() {
        return total10;
    }

    public void setTotal10(Integer total10) {
        this.total10 = total10;
    }

    public Integer getTotal1155() {
        return total1155;
    }

    public void setTotal1155(Integer total1155) {
        this.total1155 = total1155;
    }

    public Integer getTotal20() {
        return total20;
    }

    public void setTotal20(Integer total20) {
        this.total20 = total20;
    }

    public Integer getTotal721() {
        return total721;
    }

    public void setTotal721(Integer total721) {
        this.total721 = total721;
    }

    public Integer getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(Integer totalAll) {
        this.totalAll = totalAll;
    }

    public Integer getTotalAllCount() {
        return totalAllCount;
    }

    public void setTotalAllCount(Integer totalAllCount) {
        this.totalAllCount = totalAllCount;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getValueAtLeast() {
        return valueAtLeast;
    }

    public void setValueAtLeast(Integer valueAtLeast) {
        this.valueAtLeast = valueAtLeast;
    }

    public static class TokensBean {
        private String abbr;
        private String canShow;
        private String contractAddress;
        private String dateCreated;
        private Integer decimal;
        private String description;
        private String gain;
        private String github;
        private String imgUrl;
        private Boolean isTop;
        private String level;
        private String marketcap;
        private String name;
        private String nrOfTokenHolders;
        private String ownerAddress;
        private String pairUrl;
        private String priceInTrx;
        private String priceInUsd;
        private String projectSite;
        private String social_media;
        private String supply;
        private String tokenId;
        private String tokenType;
        private String transferCount;
        private String verifier;
        private Boolean vip;
        private String volume24hInTrx;
        private String whitePaper;
        private String announcement;
        private String blueTag;
        private String contractAddressLower;
        private String email;
        private String extra;
        private String greyTag;
        private String issueTime;
        private String pairId;
        private String publicTag;
        private String recentTransferCount;
        private String redTag;
        private String hash;

        public String getAbbr() {
            return abbr;
        }

        public void setAbbr(String abbr) {
            this.abbr = abbr;
        }

        public String getCanShow() {
            return canShow;
        }

        public void setCanShow(String canShow) {
            this.canShow = canShow;
        }

        public String getContractAddress() {
            return contractAddress;
        }

        public void setContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public Integer getDecimal() {
            return decimal;
        }

        public void setDecimal(Integer decimal) {
            this.decimal = decimal;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getGain() {
            return gain;
        }

        public void setGain(String gain) {
            this.gain = gain;
        }

        public String getGithub() {
            return github;
        }

        public void setGithub(String github) {
            this.github = github;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Boolean getTop() {
            return isTop;
        }

        public void setTop(Boolean top) {
            isTop = top;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getMarketcap() {
            return marketcap;
        }

        public void setMarketcap(String marketcap) {
            this.marketcap = marketcap;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNrOfTokenHolders() {
            return nrOfTokenHolders;
        }

        public void setNrOfTokenHolders(String nrOfTokenHolders) {
            this.nrOfTokenHolders = nrOfTokenHolders;
        }

        public String getOwnerAddress() {
            return ownerAddress;
        }

        public void setOwnerAddress(String ownerAddress) {
            this.ownerAddress = ownerAddress;
        }

        public String getPairUrl() {
            return pairUrl;
        }

        public void setPairUrl(String pairUrl) {
            this.pairUrl = pairUrl;
        }

        public String getPriceInTrx() {
            return priceInTrx;
        }

        public void setPriceInTrx(String priceInTrx) {
            this.priceInTrx = priceInTrx;
        }

        public String getPriceInUsd() {
            return priceInUsd;
        }

        public void setPriceInUsd(String priceInUsd) {
            this.priceInUsd = priceInUsd;
        }

        public String getProjectSite() {
            return projectSite;
        }

        public void setProjectSite(String projectSite) {
            this.projectSite = projectSite;
        }

        public String getSocial_media() {
            return social_media;
        }

        public void setSocial_media(String social_media) {
            this.social_media = social_media;
        }

        public String getSupply() {
            return supply;
        }

        public void setSupply(String supply) {
            this.supply = supply;
        }

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public String getTransferCount() {
            return transferCount;
        }

        public void setTransferCount(String transferCount) {
            this.transferCount = transferCount;
        }

        public String getVerifier() {
            return verifier;
        }

        public void setVerifier(String verifier) {
            this.verifier = verifier;
        }

        public Boolean getVip() {
            return vip;
        }

        public void setVip(Boolean vip) {
            this.vip = vip;
        }

        public String getVolume24hInTrx() {
            return volume24hInTrx;
        }

        public void setVolume24hInTrx(String volume24hInTrx) {
            this.volume24hInTrx = volume24hInTrx;
        }

        public String getWhitePaper() {
            return whitePaper;
        }

        public void setWhitePaper(String whitePaper) {
            this.whitePaper = whitePaper;
        }

        public String getAnnouncement() {
            return announcement;
        }

        public void setAnnouncement(String announcement) {
            this.announcement = announcement;
        }

        public String getBlueTag() {
            return blueTag;
        }

        public void setBlueTag(String blueTag) {
            this.blueTag = blueTag;
        }

        public String getContractAddressLower() {
            return contractAddressLower;
        }

        public void setContractAddressLower(String contractAddressLower) {
            this.contractAddressLower = contractAddressLower;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public String getGreyTag() {
            return greyTag;
        }

        public void setGreyTag(String greyTag) {
            this.greyTag = greyTag;
        }

        public String getIssueTime() {
            return issueTime;
        }

        public void setIssueTime(String issueTime) {
            this.issueTime = issueTime;
        }

        public String getPairId() {
            return pairId;
        }

        public void setPairId(String pairId) {
            this.pairId = pairId;
        }

        public String getPublicTag() {
            return publicTag;
        }

        public void setPublicTag(String publicTag) {
            this.publicTag = publicTag;
        }

        public String getRecentTransferCount() {
            return recentTransferCount;
        }

        public void setRecentTransferCount(String recentTransferCount) {
            this.recentTransferCount = recentTransferCount;
        }

        public String getRedTag() {
            return redTag;
        }

        public void setRedTag(String redTag) {
            this.redTag = redTag;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }
}
