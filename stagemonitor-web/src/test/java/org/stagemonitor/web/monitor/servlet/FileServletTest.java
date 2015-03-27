package org.stagemonitor.web.monitor.servlet;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

public class FileServletTest {

	private FileServlet fileServlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		fileServlet = new FileServlet();
		fileServlet.init(new MockServletConfig(new MockServletContext()));

		request = new MockHttpServletRequest("GET", "/stagemonitor/static/test.js");
		response = new MockHttpServletResponse();
	}

	@Test
	public void testGetStaticResource() throws Exception {
		request.setRequestURI("/stagemonitor/static/test.html");

		fileServlet.service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("test", response.getContentAsString());
		assertEquals("text/html", response.getContentType());
	}

	@Test
	public void testGetStaticResourceDirUp() throws Exception {
		request.setRequestURI("/stagemonitor/static/../test2.js");

		fileServlet.service(request, response);

		assertEquals(404, response.getStatus());
		assertEquals("", response.getContentAsString());
	}
}
