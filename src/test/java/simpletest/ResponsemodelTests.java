package simpletest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.iwebirth.controller.responsemodel.EVTreeResponseLeaf;
import com.iwebirth.controller.responsemodel.EVTreeResponseNode;
import com.iwebirth.controller.responsemodel.TreeResponseRoot;
import com.iwebirth.util.Jackson;


public class ResponsemodelTests {
	@Test
	public void jacksonTest() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyone", "hello");
		map.put("keytwo", "world");
		map.put("keythree", true);
		System.out.println(Jackson.writeValueAsString(map));
	}
	
	@Test
	public void createEVTreeResponse() throws Exception{
		EVTreeResponseLeaf leaf_one = new EVTreeResponseLeaf("1", "a", "b", "rs", "ic", "qtip_one",true);
		EVTreeResponseLeaf leaf_two = new EVTreeResponseLeaf("2", "a", "b", "rs", "ic", "",true);
		EVTreeResponseLeaf leaf_three = new EVTreeResponseLeaf("3", "a", "b", "rs", "ic", "",true);
		EVTreeResponseLeaf leaf_four = new EVTreeResponseLeaf("4", "a", "b", "rs", "ic", "",true);
		EVTreeResponseLeaf leaf_five = new EVTreeResponseLeaf("5", "a", "b", "rs", "ic","", true);
		ArrayList<EVTreeResponseLeaf> children_one = new ArrayList<EVTreeResponseLeaf>();
		children_one.add(leaf_one);
		children_one.add(leaf_two);
		children_one.add(leaf_three);
		ArrayList<EVTreeResponseLeaf> children_two = new ArrayList<EVTreeResponseLeaf>();
		children_one.add(leaf_four);
		children_one.add(leaf_five);
		EVTreeResponseNode node_one = new EVTreeResponseNode("类型1", "fd", true, children_one);
		EVTreeResponseNode node_two = new EVTreeResponseNode("类型2", "fd", false, children_two);
		ArrayList<EVTreeResponseNode> node_children = new ArrayList<EVTreeResponseNode>();
		node_children.add(node_one);
		node_children.add(node_two);
		TreeResponseRoot<EVTreeResponseNode> root = new TreeResponseRoot<EVTreeResponseNode>(node_children);
		System.out.println(Jackson.writeValueAsString(root));
	}
}
