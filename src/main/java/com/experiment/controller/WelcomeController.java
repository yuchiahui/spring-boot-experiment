package com.experiment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 根控制器
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

	@GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	String jSONObject() {
		Map map = new HashMap();
		map.put("name", "Jacky");
		map.put("age", 30);
		map.put("gender", true);

		JSONObject jSONObject = new JSONObject(map);
		return jSONObject.toString();
	}

	@GetMapping(path = "/", produces = "text/xml;charset=UTF-8")
	void index(HttpServletResponse response) {
		Document document;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException parserConfigurationException) {
			return;
		}

		//<document @me>
		//	<articles>
		//		<article @id @emotions @postedAt>
		//			<author @id>
		//				<nickname/>
		//			</author>
		//			<content/>
		//		</article>
		//	</articles>
		//</document>
		Element elementDocument = document.createElement("document");
		elementDocument.setAttribute("me", null);
		document.appendChild(elementDocument);

		Element elementArticles = document.createElement("articles");
		elementDocument.appendChild(elementArticles);

		Element elementArticle = document.createElement("article");
		elementArticle.setAttribute("id", "51f438a0-5938-4df1-af15-2fab9a5389e4");
		elementArticle.setAttribute("emotions", "0");
		elementArticle.setAttribute("postedAt", "2019-01-30 03:07:43.384239");
		elementArticles.appendChild(elementArticle);

		Element elementAuthor = document.createElement("author");
		elementAuthor.setAttribute("id", "d0f9919a-243b-11e9-b2d7-c7052609bd88");
		elementArticle.appendChild(elementAuthor);

		Element elementNickname = document.createElement("nickname");
		elementNickname.setTextContent("Chiah");
		elementAuthor.appendChild(elementNickname);

		Element elementContent = document.createElement("content");
		elementContent.setTextContent("這是一則留言");
		elementArticle.appendChild(elementContent);

		try {
			TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(response.getOutputStream()));
		} catch (TransformerFactoryConfigurationError transformerFactoryConfigurationError) {
			LOGGER.info(transformerFactoryConfigurationError.getLocalizedMessage());
		} catch (TransformerException | IOException transformerException) {
		}
	}

	@GetMapping(path = "/testxslt.aspx", produces = "text/html;charset=UTF-8")
	public ModelAndView testXslt() {
		System.out.println("/testxslt.aspx");
		Document document;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException parserConfigurationException) {
			return null;
		}

		//<document @me>
		//	<articles>
		//		<article @id @emotions @postedAt>
		//			<author @id>
		//				<nickname/>
		//			</author>
		//			<content/>
		//		</article>
		//	</articles>
		//</document>
		Element elementDocument = document.createElement("document");
		elementDocument.setAttribute("me", null);
		document.appendChild(elementDocument);

		Element elementarticles = document.createElement("articles");
		elementDocument.appendChild(elementarticles);

		Element elementArticle = document.createElement("article");
		elementArticle.setAttribute("id", "51f438a0-5938-4df1-af15-2fab9a5389e4");
		elementArticle.setAttribute("emotions", "0");
		elementArticle.setAttribute("postedAt", "2019-01-30 03:07:43.384239");
		elementarticles.appendChild(elementArticle);

		Element elementAuthor = document.createElement("author");
		elementAuthor.setAttribute("id", "d0f9919a-243b-11e9-b2d7-c7052609bd88");
		elementArticle.appendChild(elementAuthor);

		Element elementNickname = document.createElement("nickname");
		elementNickname.setTextContent("Chiah");
		elementAuthor.appendChild(elementNickname);

		Element elementContent = document.createElement("content");
		elementContent.setTextContent("這是一則留言");
		elementArticle.appendChild(elementContent);

		ModelAndView modelAndView = new ModelAndView("test");
		modelAndView.getModelMap().addAttribute(new DOMSource(document));
		return modelAndView;
	}

	@GetMapping(path = "/xslt.aspx", produces = "text/html;charset=UTF-8")
	public ModelAndView xslt(HttpServletRequest request, HttpServletResponse response) {
		Document document;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException parserConfigurationException) {
			return null;
		}
		Element documentElement = document.createElement("document");
		document.appendChild(documentElement);

		/*
		 性別
		 */
		Element gendersElement = document.createElement("genders");
		documentElement.appendChild(gendersElement);
		// 男
		Element maleElement = document.createElement("gender");
		maleElement.setAttribute("checked", null);
		maleElement.setTextContent("男");
		gendersElement.appendChild(maleElement);
		// 女
		Element femaleElement = document.createElement("gender");
		femaleElement.setAttribute("checked", null);
		femaleElement.setTextContent("女");
		gendersElement.appendChild(femaleElement);

		/*
		 身份
		 */
		Element rolesElement = document.createElement("roles");
		documentElement.appendChild(rolesElement);
		// P端
		Element pRoleElement = document.createElement("role");
		pRoleElement.setAttribute("checked", null);
		pRoleElement.setTextContent("P端");
		rolesElement.appendChild(pRoleElement);
		// B端
		Element bRoleElement = document.createElement("role");
		bRoleElement.setAttribute("checked", null);
		bRoleElement.setTextContent("B端");
		rolesElement.appendChild(bRoleElement);
		// C端
		Element cRoleElement = document.createElement("role");
		cRoleElement.setAttribute("checked", null);
		cRoleElement.setTextContent("C端");
		rolesElement.appendChild(cRoleElement);

		/*
		 地區
		 */
		Element locationsElement = document.createElement("locations");
		documentElement.appendChild(locationsElement);

		// 資料
		@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
		List<Map<String, String>> locations = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("1", "台北");
		map.put("2", "桃園");
		map.put("3", "台中");
		map.put("4", "台南");
		map.put("5", "高雄");
		locations.add(map);
		locations.forEach((location) -> {
			Element locationElement = document.createElement("location");
			locationElement.setAttribute("id", location.get(""));
		});

//		try {
//			TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(response.getOutputStream()));
//		} catch (TransformerFactoryConfigurationError transformerFactoryConfigurationError) {
//		} catch (TransformerException transformerException) {
//		} catch (IOException ioException) {
//		}
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.getModelMap().addAttribute(new DOMSource(document));
		return modelAndView;
	}
}
