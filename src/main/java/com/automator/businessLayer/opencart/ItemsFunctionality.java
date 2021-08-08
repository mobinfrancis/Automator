package com.automator.businessLayer.opencart;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.automator.pageComponent.home.ItemPageComponent;

public class ItemsFunctionality extends BaseFunctionality {

	private ItemPageComponent itemPageComponent;
	private static final Logger log = Logger.getLogger(ItemsFunctionality.class);
	private String navbarItemName;

	public void validateNavbarItemIsEnabled(String navbarItem) {
		this.navbarItemName = navbarItem;
		this.itemPageComponent = new ItemPageComponent(driver);
		switch (this.navbarItemName) {
		case "Desktops":
			Assert.assertTrue(this.itemPageComponent.getDesktopsNavbar().isEnabled());
			break;
		case "LaptopsAndNotebooks":
			Assert.assertTrue(this.itemPageComponent.getLaptopsAndNotebooksNavbar().isEnabled());
			break;
		case "Components":
			Assert.assertTrue(this.itemPageComponent.getComponentsNavbar().isEnabled());
			break;
		case "Tablets":
			Assert.assertTrue(this.itemPageComponent.getTabletsNavbar().isEnabled());
			break;
		case "Software":
			Assert.assertTrue(this.itemPageComponent.getSoftwareNavbar().isEnabled());
			break;
		case "PhonesAndPDAs":
			Assert.assertTrue(this.itemPageComponent.getPhonesAndPDAsNavbar().isEnabled());
			break;
		case "Cameras":
			Assert.assertTrue(this.itemPageComponent.getCamerasNavbar().isEnabled());
			break;
		case "MP3Players":
			Assert.assertTrue(this.itemPageComponent.getMP3PlayersNavbar().isEnabled());
			break;
		}

	}

}
