package ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
* Adapter class for the interface LayoutManager. Used to create an easier way to crete layouts.
*
* @author Fabricio Tomás <a href="https://github.com/Fabricio-Tomas">github-profile</a>
*/
public class LayoutAdapter implements LayoutManager {

	@Override
	public void addLayoutComponent(String arg0, Component arg1) {

	}

	@Override
	public void layoutContainer(Container c) {

	}

	@Override
	public Dimension minimumLayoutSize(Container arg0) {
		return null;
	}

	@Override
	public Dimension preferredLayoutSize(Container arg0) {
		return null;
	}

	@Override
	public void removeLayoutComponent(Component arg0) {

	}

}
