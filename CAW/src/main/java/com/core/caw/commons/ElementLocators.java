package com.core.caw.commons;

public class ElementLocators {

    public interface Common {
        public static final String LOGO = "img[alt='My Store']";
    }

    public interface HomePage {
        public static final String INPUT_SEARCHBOX = "[class=\"search_query form-control ac_input\"]";
        public static final String INPUT_SEARCHBOX_SUBMIT = "[name=\"submit_search\"]";
        public static final String INPUT_SEARCHBOX_DrpDown = ".ac_results ul";
    }

    public interface SearchItemPage {
        public static final String SEARCH_LABEL = ".lighter";
        public static final String TSHIRT_TAB = "//div[@id='block_top_menu']/ul/li[3]/a[@title='T-shirts']";
        public static final String TSHIRT_BAR = "//*[contains(text(), 'T-shirts')]";
        public static final String ITEM_SIZE_S = "[name=\"layered_id_attribute_group_1\"]";
        public static final String ITEM_SIZE_M = "[name=\"layered_id_attribute_group_2\"]";
        public static final String ITEM_SIZE_L = "[name=\"layered_id_attribute_group_3\"]";
    }

    public interface ContactPage {
        public static final String CONTACT_US = "div#contact-link > a[title='Contact Us']";
        public static final String CHOOSE_FILE = "#uniform-fileUpload";
    }

}
